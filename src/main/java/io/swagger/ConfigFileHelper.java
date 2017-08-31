package io.swagger;

import io.swagger.model.Backend;
import io.swagger.model.Frontend;
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

                Map<String, Object> c = new HashMap<String, Object>();
                Map<String, Frontend> fes = new HashMap<String, Frontend>();
                c.put("frontends", fes);
                Map<String, Backend> bes = new HashMap<String, Backend>();
                c.put("backends", bes);

                saveObjectFile(c);
                return c;
            }
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(configBinPath));
            Map<String, Object> config = (Map<String, Object>) is.readObject();
            is.close();
            return config;
        } catch (Exception e) {
            logger.error("Error when save config bin file.", e);
            throw e;
        }
    }

}
