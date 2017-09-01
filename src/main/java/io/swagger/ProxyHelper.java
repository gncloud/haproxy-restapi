package io.swagger;

import io.swagger.model.ACL;
import io.swagger.model.Backend;
import io.swagger.model.Frontend;
import io.swagger.model.Service;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by swsong on 17. 8. 30..
 */
@Component
public class ProxyHelper {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(ProxyHelper.class);

    private Random random = new Random(System.nanoTime());
    private String tempFilePath = System.getProperty("java.io.tmpdir");
    private String TEMPLATE_NAME = "templates/haproxy.cfg.vm";

    private String appHome           = "/var/lib/haproxy-restapi";
    private String haproxyBinaryPath = appHome + "/bin/haproxy";
    private String haproxyConfigPath = appHome + "/conf/haproxy.cfg";
    private String pidFilePath       = appHome + "/bin/haproxy.pid";

    private Process process;

    private final ReadWriteLock lock;
    private final Lock writeLock;

    private VelocityEngine engine;

    public ProxyHelper() {
        engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();

        lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
    }

    protected String renderTemplate(Map<String, Service> config) {
        VelocityContext context = new VelocityContext();

        List<Frontend> frontends = new ArrayList<>();
        List<Backend> backends = new ArrayList<>();
        context.put("frontends", frontends);
        context.put("backends", backends);

        Iterator<Map.Entry<String, Service>> iter = config.entrySet().iterator();

        while(iter.hasNext()) {
            Map.Entry<String, Service> e = iter.next();
            String id = e.getKey();
            Service service = e.getValue();

            Integer bindPort = service.getBindPort();
            String mode = service.getMode();
            String subdomain = service.getSubdomain();
            Integer timeout = service.getTimeout();
            String host = service.getHost();
            Integer port = service.getPort();

            if(bindPort == null){
                throw new ConfigInvalidException("bindPort cannot be empty.");
            } else if(host == null) {
                throw new ConfigInvalidException("host cannot be empty.");
            } else if(port == null) {
                throw new ConfigInvalidException("port cannot be empty.");
            }

            String name = makeName(mode, port);
            Frontend fe = new Frontend();
            fe.setName(name);
            fe.setBindIp("*");
            fe.setBindPort(bindPort);
            fe.setMode(mode);
            fe.setTimeoutClient(timeout);
            fe.setTimeoutServer(timeout);
            fe.setTimeoutConnect(1000); //1000ms


            if("http".equalsIgnoreCase(mode)) {
                ACL acl = new ACL();
                acl.setBackend(name);

                if(subdomain.equals("") || subdomain == null) {
                    subdomain = "_";
                    //서브도메인이 없으면 패턴도 없다. 즉, default_backend로 처리.
                } else {
                    acl.setName(subdomain);
                    acl.setPattern("hdr_beg(host) " + subdomain + ".");
                }
                fe.getAclsNotNull().put(subdomain, acl);
            }

            frontends.add(fe);

            Backend be = new Backend();
            be.setName(name);
            be.setMode(mode);
            be.setHost(host);
            be.setPort(port);
            backends.add(be);
        }

        org.apache.velocity.Template template = engine.getTemplate(TEMPLATE_NAME, "utf-8");
        StringWriter stringWriter = new StringWriter();

        template.merge(context, stringWriter);
        String configString = stringWriter.toString();

        return configString;
    }

    private String makeName(String mode, int port) {
        return mode+"_"+port;
    }

    public String applyConfig(Map<String, Service> config) throws ConfigInvalidException {
        writeLock.lock();

        try {
            String tempFileName = Long.toString(random.nextLong());
            File tempFile = new File(tempFilePath, tempFileName);

            //1. 임시 저장
            String configString = renderTemplate(config);
            logger.info("applyConfig => {}", configString);
            Writer fileWriter = new OutputStreamWriter(new FileOutputStream(tempFile));
            fileWriter.write(configString);
            fileWriter.close();


            //2. validate
            Process process = new ProcessBuilder(haproxyBinaryPath
                                        , "-c"
                                        , "-f"
                                        , tempFile.getPath())
                                        .inheritIO().start();
            process.waitFor();
            if(process.exitValue() != 0){
                throw new Exception("haproxy.cfg is invalid! >> \n" + configString);
            }


            //3. 덮어쓰기.
            File configFile = new File(haproxyConfigPath);
            FileCopyUtils.copy(tempFile, configFile);


            //4. PID정보 수집
            String pid = "";
            File pidFile = new File(pidFilePath);
            if(pidFile.isFile()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pidFile));
                String tmp = "";
                while( (tmp = bufferedReader.readLine()) != null){
                    pid += tmp + " ";
                }
                bufferedReader.close();
            }

            //5. restart -sf
            restartProxy(pid);

            return configString;

        } catch (Exception e) {
            throw new ConfigInvalidException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public void restartProxy(String pid) throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder(haproxyBinaryPath
                , "-f", haproxyConfigPath
                , "-p", pidFilePath
                , "-sf", pid)
                .inheritIO();
        logger.info("processBuilder => {}", processBuilder.command());
        this.process = processBuilder.start();
        logger.info("haproxy update ok!. prevPid[{}]", pid);
    }

    public void stopProxy() {
        process.destroy();
    }

}
