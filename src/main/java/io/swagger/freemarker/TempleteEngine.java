package io.swagger.freemarker;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

@Component
public class TempleteEngine {

    private static Logger logger = LoggerFactory.getLogger(TempleteEngine.class);

    @Value("${freemarker.template.path}")
    private String templatePath;
    @Value("${freemarker.temp.path}")
    private String tempPath;
    @Value("${freemarker.temp.filename}")
    private String tempFileName;
    @Value("${freemarker.template.subffix}")
    private String subffix;

    public synchronized String getHaproxy(String ftlFile, Map<String, String> data){

        Configuration config = null;
        Writer writer = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            config = new Configuration(Configuration.VERSION_2_3_23);
            config.setObjectWrapper(Configuration.getDefaultObjectWrapper(Configuration.VERSION_2_3_23));
            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templatePath));

            TemplateLoader[] templateLoader = new TemplateLoader[]{fileTemplateLoader};
            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoader);
            config.setTemplateLoader(multiTemplateLoader);

            Template template = config.getTemplate(ftlFile + subffix);

            writer = new StringWriter();
            template.process(data, writer);
            stringBuffer.append(writer.toString());

            writer.flush();
            writer.close();

            fileWrite(stringBuffer);

        } catch (TemplateException e) {
            logger.debug("TemplateException =>" + e.getMessage());
        } catch (TemplateNotFoundException e) {
            logger.debug("TemplateNotFoundException => " + e.getMessage());
        } catch (MalformedTemplateNameException e) {
            logger.debug("MalformedTemplateNameException =>" + e.getMessage());
        } catch (ParseException e) {
            logger.debug("ParseException => " + e.getMessage());
        } catch (IOException e) {
            logger.debug("IOException =>" + e.getMessage());
        }
        return stringBuffer.toString();
    }


    public void fileWrite(StringBuffer stringBuffer) throws IOException {

        logger.debug("haproxy result => {}",stringBuffer.toString());

        File tempDir = new File(tempPath);
        if(!tempDir.isDirectory()){
            tempDir.mkdirs();
        }
        File tempHaproxy = new File(tempPath  + tempFileName);
        if(!tempHaproxy.isFile()){
            tempHaproxy.createNewFile();
        }

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempHaproxy));
        bufferedOutputStream.write(stringBuffer.toString().getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

}
