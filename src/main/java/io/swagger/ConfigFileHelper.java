package io.swagger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * Created by swsong on 17. 8. 31..
 */
@Component
public class ConfigFileHelper {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(ConfigFileHelper.class);

    @Value("${haproxy.config.bin.path}")
    private String configBinPath;

    public ConfigFileHelper() {
    }

    public File saveObjectFile(Map<String, Object> config) throws IOException {
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

    public Map<String, Object> loadObjectFile() throws Exception {
        try {
            File file = new File(configBinPath);
            if(!file.exists()) {
                return null;
            }
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(configBinPath));
            Map<String, Object> config = (Map<String, Object>) is.readObject();
            return config;
        } catch (Exception e) {
            logger.error("Error when save config bin file.", e);
            throw e;
        }
    }

}
