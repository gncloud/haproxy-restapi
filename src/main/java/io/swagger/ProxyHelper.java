package io.swagger;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

/**
 * Created by swsong on 17. 8. 30..
 */

@Component
public class ProxyHelper {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(ProxyHelper.class);

    private Configuration cfg;
    private Random random = new Random(System.nanoTime());
    private String tempFilePath = System.getProperty("java.io.tmpdir");
    private String TEMPLATE_NAME = "haproxy.cfg.ftl";

    private String appHome           = "/var/lib/haproxy-restapi";
    private String haproxyBinaryPath = appHome + "/bin/haproxy";
    private String haproxyConfigPath = appHome + "/conf/haproxy.cfg";
    private String pidFilePath       = appHome + "/bin/haproxy.pid";

    private Process process;

    private final ReadWriteLock lock;
    private final Lock writeLock;

    public ProxyHelper() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_25));

        lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
    }
    public String applyConfig(Map config) throws ConfigInvalidException {
        writeLock.lock();

        try {
            //1. 임시 저장
            StringWriter writer = new StringWriter();
            Template temp = cfg.getTemplate(TEMPLATE_NAME);
            temp.process(config, writer);
            String configString = writer.toString();
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

}
