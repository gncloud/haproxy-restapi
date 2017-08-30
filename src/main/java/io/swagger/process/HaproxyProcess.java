package io.swagger.process;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class HaproxyProcess {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(HaproxyProcess.class);

    @Value("${freemarker.temp.path}")
    private String tempPath;

    @Value("${freemarker.temp.filename}")
    private String filename;

    @Value("${haproxy.config}")
    private String haproxyConfig;

    public boolean start(){

        logger.debug("start..");
        logger.debug("tempFile : {}", tempPath + filename);
        logger.debug("configFile : {}", haproxyConfig);
        try{
            File tempFile = new File(tempPath + filename);
            File haproxyFile = new File(haproxyConfig);

            if(!haproxyFile.isFile()){
                haproxyFile.createNewFile();
            }
            FileCopyUtils.copy(tempFile, haproxyFile);
            List<String> argument = new LinkedList<>();

            //argument.add("haproxy -f " + haproxyConfig + " -p pid.txt -sf $(cat pid.txt)");
//            argument.add("/bin/bash");
//            argument.add("docker ps");
            argument.add("docker");
            argument.add("ps");

            ProcessBuilder processBuilder = new ProcessBuilder(argument);
            processBuilder.inheritIO();
            processBuilder.start();
        } catch(IOException e){
            logger.debug("process start fail : {}", e.getMessage());
            return false;
        }
        return true;
    }

    public boolean stop() {
        List<String> argument = new LinkedList<>();
        argument.add("kill -9 $(cat pid.txt)");
        ProcessBuilder processBuilder = new ProcessBuilder(argument);
        try{
            processBuilder.inheritIO();
            processBuilder.start();
        } catch(IOException e){
            logger.debug("process stop fail : {}", e.getMessage());
            return false;
        }
        return true;
    }
}
