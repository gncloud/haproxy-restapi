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

    @Value("${app.home}")
    private String appHomePath;

    @Value("${haproxy.config.path}")
    private String haproxyConfigPath;

    @Value("${haproxy.binary.path}")
    private String haproxyBinPath;

    @Value("${haproxy.binary.path}/haproxy.pid")
    private String pidFilePath;

    private Process haproxyProcess;

    public boolean start(){

        logger.debug("start..");
        logger.debug("tempFile : {}", tempPath + filename);
        logger.debug("configFile : {}", haproxyConfigPath);
        try{
            File tempFile = new File(tempPath + filename);
            File haproxyFile = new File(haproxyConfigPath);

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

//            ProcessBuilder processBuilder = new ProcessBuilder(argument);
            String pidFilePath = appHomePath+"/haproxy.pid";
            ProcessBuilder processBuilder = new ProcessBuilder(haproxyBinPath, "-p", pidFilePath, "-sf", "$(cat "+pidFilePath+")");
            processBuilder.inheritIO();
            processBuilder.start();
        } catch(IOException e){
            logger.debug("process start fail : {}", e.getMessage());
            return false;
        }
        return true;
    }

    public boolean restart(File tempFile) throws Exception {
        File haproxyFile = new File(haproxyConfigPath);

        if(!haproxyFile.isFile()){
            haproxyFile.createNewFile();
        }
        FileCopyUtils.copy(tempFile, haproxyFile);


        ProcessBuilder processBuilder = new ProcessBuilder(haproxyBinPath, "-p", pidFilePath, "-sf", "$(cat "+pidFilePath+")");
        processBuilder.inheritIO();
        haproxyProcess = processBuilder.start();

        return true;

    }

    public boolean stop() {
        haproxyProcess.destroy();
        return true;
    }
}
