package io.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
//import freemarker.template.Configuration;
//import freemarker.template.DefaultObjectWrapper;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;
import io.swagger.model.Backend;
import io.swagger.model.Frontend;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by swsong on 17. 8. 30..
 */

@Component
public class ProxyHelper {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(ProxyHelper.class);

//    private Configuration cfg;
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
    public ProxyHelper() throws IOException {
        engine = new VelocityEngine();
//        engine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "/templates/");
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();

//        cfg = new Configuration(Configuration.VERSION_2_3_25);
//        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        cfg.setLogTemplateExceptions(false);
//        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_25));

        lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
    }
    public String applyConfig(Map config) throws ConfigInvalidException {
        writeLock.lock();

        try {
            //1. 임시 저장
            VelocityContext context = new VelocityContext();
            Map<String, Frontend> frontends = (Map<String, Frontend>) config.get("frontends");
            Map<String, Backend> backends = (Map<String, Backend>) config.get("backends");
            context.put("frontends", frontends);
            context.put("backends", backends);

//            Template temp = cfg.getTemplate(TEMPLATE_NAME);
            org.apache.velocity.Template template = engine.getTemplate(TEMPLATE_NAME, "utf-8");
            StringWriter stringWriter = new StringWriter();

            template.merge(context, stringWriter);
//            temp.process(config, writer);
            String configString = stringWriter.toString();
            logger.info("config : \n{}", configString);

            String tempFileName = Long.toString(random.nextLong());
            File tempFile = new File(tempFilePath, tempFileName);
            Writer fileWriter = new OutputStreamWriter(new FileOutputStream(tempFile));
            fileWriter.write(configString);
            fileWriter.close();


            //2. validate
            process = new ProcessBuilder(haproxyBinaryPath
                                        , "-c"
                                        , "-f"
                                        , tempFile.getPath())
                                        .inheritIO().start();
            process.waitFor();
            if(process.exitValue() != 0){
                throw new Exception("haproxy.cfg invalidate");
            }


            //3. 덮어쓰기.
            File configFile = new File(haproxyConfigPath);
            FileCopyUtils.copy(tempFile, configFile);


            //4. PID정보 수집
            String pid = "";
            File pidFile = new File(pidFilePath);
            if(pidFile.isFile()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pidFile));
                String tmp = new String();
                while( (tmp = bufferedReader.readLine()) != null){
                    pid += tmp + " ";
                }
                bufferedReader.close();
            }

            //5. restart -sf
            process = new ProcessBuilder(haproxyBinaryPath
                    , "-f", haproxyConfigPath
                    , "-p", pidFilePath
                    , "-sf", pid)
                    .inheritIO().start();
            logger.info("haproxy update ok!. prevPid[{}]", pid);

            return configString;

        } catch (Exception e) {
            throw new ConfigInvalidException(e);
        } finally {
            writeLock.unlock();
        }
    }


//    protected String makeConfigString(VelocityContext context) {
//        VelocityEngine engine = new VelocityEngine();
//        engine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templateFilePath);
//        engine.init();
//
//        org.apache.velocity.Template template = engine.getTemplate(TEMPLATE_NAME, "utf-8");
//        StringWriter stringWriter = new StringWriter();
//
//        template.merge(context, stringWriter);
//        return stringWriter.toString();
//    }

//    public String updateProxyConfig() {
//        VelocityContext context = new VelocityContext();
//        List<Frontend> frontendList = new ArrayList<>();
//        List<Backend> backendList = new ArrayList<>();
//
//        //1. topology구성도로 context에 값을 넣어준다.
//        fillTopologyToContext(frontendList, backendList);
//
//        //2. marathon을 통해 app별 listening 상태를 받아와서 context에 넣어준다.
//        fillServiceToContext(frontendList, backendList);
//
//        context.put(FRONTEND_LIST, frontendList);
//        context.put(BACKEND_LIST, backendList);
//
//        String configString = makeConfigString(context);
//        proxyUpdateQueue.offer(configString);
//        return configString;
//    }

}
