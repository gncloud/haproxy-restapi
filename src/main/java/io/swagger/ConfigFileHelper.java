package io.swagger;

import io.swagger.model.Backend;
import io.swagger.model.Frontend;
import io.swagger.model.Service;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by swsong on 17. 8. 31..
 */
@Component
public class ConfigFileHelper {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(ConfigFileHelper.class);

    private String configBinPath = "/var/lib/haproxy-restapi/conf/haproxy.bin";

    public ConfigFileHelper() {
        logger.info("new ConfigFileHelper ");
    }

    public File saveObjectFile(Map<String, Service> config) throws IOException {
        try {
            File file = new File(configBinPath);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(config);
            oos.flush();
            oos.close();
            logger.info("Saved object file = {}", file.getAbsolutePath(), file.length());
            return file;
        } catch (Exception e) {
            logger.error("Error when save config bin file.", e);
            throw e;
        }
    }

    public Map<String, Service> loadObjectFile() throws Exception {
        try {
            File file = new File(configBinPath);
            if(!file.exists()) {

                Map<String, Service> c = new HashMap<String, Service>();
                saveObjectFile(c);
                return c;
            }
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(configBinPath));
            Map<String, Service> config = (Map<String, Service>) is.readObject();


            logger.info("loadObjectFile config data {}", config);

            is.close();
            return config;
        } catch (Exception e) {
            logger.error("Error when save config bin file.", e);
            throw e;
        }
    }

}
