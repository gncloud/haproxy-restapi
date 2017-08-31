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
import java.util.Map;
import java.util.Random;

/**
 * Created by swsong on 17. 8. 30..
 */

@Component
public class TemplateHelper {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(TemplateHelper.class);

    private Configuration cfg;
    private String TEMPLATE_NAME = "haproxy.cfg.ftl";

    @Value("${app.home}")
    private String appHome           = "/var/lib/haproxy-restapi";
    private String haproxyBinaryPath = appHome + "/bin/haproxy";
    private String haproxyConfigPath = appHome + "/conf/haproxy.cfg";
    private String pidFilePath       = appHome + "/bin/haproxy.pid";

    private Process process;

    public TemplateHelper() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_25));
    }
    public String format(Map<String, Object> config) throws ConfigInvalidException {
        try {
            StringWriter out = new StringWriter();
            Template temp = cfg.getTemplate(TEMPLATE_NAME);
            temp.process(config, out);
            return out.toString();
        } catch (Exception e) {
            throw new ConfigInvalidException(e);
        }
    }

}
