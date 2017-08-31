package io.swagger;

import freemarker.core.ParseException;
import freemarker.template.*;
import io.swagger.model.Config;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Random;

/**
 * Created by swsong on 17. 8. 30..
 */

@Component
public class ProxyHelper {

    private Configuration cfg;
    private Random random = new Random(System.nanoTime());
    private String tempFilePath = System.getProperty("tmp.dir");
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

            Template temp = cfg.getTemplate("haproxy.cfg.ftl");
            Writer out = new OutputStreamWriter(System.out);
            temp.process(c, out);


            //1. 임시 저장
            String tempFileName = Long.toString(random.nextLong());
            File tempFile = new File(tempFilePath, tempFileName);


            //2. validate

            //3. 덮어쓰기.

            //4. restart -sf


        } catch (Exception e) {
            throw new ConfigInvalidException(e);
        }

        return true;

    }
}
