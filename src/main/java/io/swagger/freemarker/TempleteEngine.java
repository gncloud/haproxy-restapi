package io.swagger.freemarker;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.*;
import io.swagger.model.Config;
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
        File tempHaproxy = new File(tempPath  + tempFileName + ".temp");
        if(!tempHaproxy.isFile()){
            tempHaproxy.createNewFile();
        }

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempHaproxy));
        bufferedOutputStream.write(stringBuffer.toString().getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public void dataWrite(Config config) throws IOException {

        File tempDir = new File(tempPath);
        if(!tempDir.isDirectory()){
            tempDir.mkdirs();
        }
        File tempHaproxy = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutput objectOutput = null;

        tempHaproxy = new File(tempPath  + tempFileName + ".data");
        if(!tempHaproxy.isFile()){
            tempHaproxy.createNewFile();
        }

        fileOutputStream = new FileOutputStream(tempHaproxy);
        objectOutput = new ObjectOutputStream(fileOutputStream);
        objectOutput.writeObject(config);
        objectOutput.flush();
        objectOutput.close();
        fileOutputStream.close();

    }

    public Config dateReader() {
        File tempHaproxy = new File(tempPath  + tempFileName + ".data");
        if(!tempHaproxy.isFile()){
            return new Config();
        }
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Config config = null;
        try {
            fileInputStream = new FileInputStream(tempHaproxy);
            objectInputStream = new ObjectInputStream(fileInputStream);

            System.out.println(objectInputStream.readObject());

        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("datareader fail {}", e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.debug("datareader fail {}", e.getMessage());
        } finally {
            try {
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e){
                logger.debug("datareader fail {}", e.getMessage());
            }
        }
        return config;
    }


}
