package io.swagger;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;
import java.util.Random;

/**
 * Created by swsong on 17. 8. 31..
 */
@Component
public class ConfigFileHelper {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(ConfigFileHelper.class);

    private Random random = new Random(System.nanoTime());
    private String tempFilePath = System.getProperty("java.io.tmpdir");


    private String objectFilePath = "";


    public ConfigFileHelper() {

    }

    public void saveObjectFile(Map<String, Object> config) throws IOException {
        try {
//            OutputStream os = new ObjectOutputStream();
            //TODO
        } catch(Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    public Map<String, Object> loadObjectFile() {
        try {
//            InputStream os = new ObjectInputStream();
            //TODO
        } catch(Exception e) {
            logger.error("", e);
            throw e;
        }


        return null;
    }




    public File saveTempFile(String configStr) {
        try {
            String tempFileName = Long.toString(random.nextLong());
            File tempFile = new File(tempFilePath, tempFileName);
            Writer out = new OutputStreamWriter(new FileOutputStream(tempFile));
            out.write(configStr);
            out.close();
            return tempFile;
        } catch(Exception e) {
            logger.error("", e);
        }

        return null;
    }



}
