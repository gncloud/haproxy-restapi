package io.swagger;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
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

    public ProxyHelper() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_25);
//        cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_25));
    }
    public boolean applyConfig(Map c) throws ConfigInvalidException {

        try {
            Template temp = cfg.getTemplate(TEMPLATE_NAME);
//            Writer out = new OutputStreamWriter(System.out);
//            temp.process(c, out);


            //1. 임시 저장
            String tempFileName = Long.toString(random.nextLong());
            File tempFile = new File(tempFilePath, tempFileName);
            Writer out = new OutputStreamWriter(new FileOutputStream(tempFile));
            temp.process(c, out);

            //2. validate
            process = new ProcessBuilder(haproxyBinaryPath, "-c", "-f", tempFile.getPath()).inheritIO().start();
            process.waitFor();
            if(process.exitValue() != 0){
                throw new Exception("haproxy.cfg invalidate");
            }

            //3. 덮어쓰기.
            File configFile = new File(haproxyConfigPath);
            FileCopyUtils.copy(tempFile, configFile);


            //4. restart -sf
            process = new ProcessBuilder(haproxyBinaryPath, "-d", "-f", haproxyConfigPath).inheritIO().start();
            logger.info("haproxy update ok");

        } catch (Exception e) {
            throw new ConfigInvalidException(e);
        }

        return true;
    }

}
