package io.swagger.freemarker;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.*;
import io.swagger.model.ACLs;
import io.swagger.model.Config;
import io.swagger.model.Frontend;
import io.swagger.model.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TempleteEngine {

    private static Logger logger = LoggerFactory.getLogger(TempleteEngine.class);

    @Value("${freemarker.template.path}")
    private String templatePath;
    private String subffix;


    private String tempPath     = "";
    private String tempFileName = "";


    public synchronized String renderTemplate(Config regiConfig){

        Configuration config = null;
        Writer writer = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            config = new Configuration(Configuration.VERSION_2_3_23);
            config.setObjectWrapper(Configuration.getDefaultObjectWrapper(Configuration.VERSION_2_3_23));
            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File("/Users/joonwookim/Projects/haproxy-restapi/src/main/resources/template/"));

            TemplateLoader[] templateLoader = new TemplateLoader[]{fileTemplateLoader};
            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(templateLoader);
            config.setTemplateLoader(multiTemplateLoader);

            Template template = config.getTemplate("test.cfg.ftl");
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> renderData = new HashMap<>();
            renderData.put("global", objectMapper.convertValue(regiConfig.getGlobal(),Map.class));
            renderData.put("defaults", objectMapper.convertValue(regiConfig.getDefaults(),Map.class));

            Iterator<Map.Entry<String,Frontend>> iterator = regiConfig.getFrontends().entrySet().iterator();
            Map<String, Object> typeConvertMap = new HashMap<>();

            Map<String, Object> acls = new HashMap<>();
            while (iterator.hasNext()){
                Map.Entry<String, Frontend> entry = iterator.next();
                if(entry.getValue() != null && entry.getValue().getAcls() != null && !entry.getValue().getAcls().isEmpty()){
                    acls.put(entry.getKey(), objectMapper.convertValue(entry.getValue().getAcls(), Map.class));
                }
                typeConvertMap.put(entry.getKey(), objectMapper.convertValue(entry.getValue(),Map.class));
            }

            renderData.put("frontends", typeConvertMap);
            renderData.put("acls", acls);

            renderData.put("backend", objectMapper.convertValue(regiConfig.getBackends(),Map.class));

            writer = new StringWriter();
            template.process(renderData, writer);
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

        File tempHaproxy = null;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempHaproxy));
        bufferedOutputStream.write(stringBuffer.toString().getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public void dataWrite(Config config) throws IOException {

        File tempHaproxy = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutput objectOutput = null;

        fileOutputStream = new FileOutputStream(tempHaproxy);
        objectOutput = new ObjectOutputStream(fileOutputStream);
        objectOutput.writeObject(config);
        objectOutput.flush();
        objectOutput.close();
        fileOutputStream.close();

    }

    public Config dateReader() {
        File tempHaproxy = null;//;;new File(tempPath  + tempFileName + ".data");
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
