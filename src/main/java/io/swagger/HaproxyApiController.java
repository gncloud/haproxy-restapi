package io.swagger;

import io.swagger.model.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by swsong on 17. 8. 31..
 */

@RestController
public class HaproxyApiController implements HaproxyApi {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(HaproxyApiController.class);


    Map<String, Object> config;
    ReentrantReadWriteLock lock;

    @Autowired
    private TemplateHelper templateHelper;
    @Autowired
    private ConfigFileHelper configFileHelper;

    public HaproxyApiController() {

        config = configFileHelper.loadObjectFile();
        lock = new ReentrantReadWriteLock();
    }

    private void loadConfig() {

    }

    private void saveConfig() {

    }

    private void applyConfig(Map<String, Object> newConfig) {

        String configStr = templateHelper.format(newConfig);

        logger.info("configStr : \n{}", configStr);

        //1. 임시 저장
        File tempFile = configFileHelper.saveTempFile(configStr);

        //2. validation.


        //3. overwrite


        //4. restart haproxy


    }

    @Override
    public ResponseEntity<Map<String, Object>> getConfig() {
        ReentrantReadWriteLock.WriteLock readLock = lock.writeLock();
        readLock.lock();
        try {
            return new ResponseEntity<Map<String, Object>>(config, HttpStatus.OK);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> postService(@PathVariable("id") String id, @Valid @RequestBody Service service) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Map<String, Object> newConfig = cloneConfig();
            Map<String, Frontend> frontends = (Map<String, Frontend>) newConfig.get("frontends");
            Map<String, Backend> backends = (Map<String, Backend>) newConfig.get("backends");

            Frontend frontend = service.getFrontend();
            if (frontend != null) {
                Frontend old = frontends.put(id, frontend);
                if (old != null) {
                    logger.warn("frontend replaced : {}", id);
                }
            }

            Backend backend = service.getBackend();
            if (backend != null) {
                Backend old = backends.put(id, backend);
                if (old != null) {
                    logger.warn("backend replaced : {}", id);
                }
            }

            applyConfig(newConfig);
            return new ResponseEntity<Map<String, Object>>(config, HttpStatus.OK);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteService(@PathVariable("id") String id) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Map<String, Object> newConfig = cloneConfig();
            Map<String, Frontend> frontends = (Map<String, Frontend>) newConfig.get("frontends");
            Map<String, Backend> backends = (Map<String, Backend>) newConfig.get("backends");

            frontends.remove(id);
            backends.remove(id);

            applyConfig(newConfig);

            return new ResponseEntity<Map<String, Object>>(config, HttpStatus.OK);
        } finally {
            writeLock.unlock();
        }
    }


    private Map<String, Object> cloneConfig() {

        Map<String, Frontend> frontends = (Map<String, Frontend>) config.get("frontends");
        Map<String, Backend> backends = (Map<String, Backend>) config.get("backends");

        Map<String, Object> newConfig = new HashMap<>();

        //frontends
        Map<String, Frontend> newFrontends = new HashMap<String, Frontend>();
        newConfig.put("frontends", newFrontends);

        if (frontends != null) {
            Iterator<Map.Entry<String, Frontend>> iter = frontends.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Frontend> e = iter.next();
                String key = e.getKey();
                Frontend fe = e.getValue();
                //clone fe
                Frontend newFe = new Frontend();
                newFe.setName(fe.getName());
                newFe.setMode(fe.getMode());
                newFe.setBindIp(fe.getBindIp());
                newFe.setBindPort(fe.getBindPort());
                newFe.setTimeoutClient(fe.getTimeoutClient());
                newFe.setTimeoutConnect(fe.getTimeoutConnect());
                newFe.setTimeoutServer(fe.getTimeoutServer());
                newFe.setDefaultBackend(fe.getDefaultBackend());
                newFe.setAclBackend(fe.getAclBackend());
                newFe.setAclPattern(fe.getAclPattern());

                newFrontends.put(key, newFe);
            }
        }


        //backends
        Map<String, Backend> newBackends = new HashMap<String, Backend>();
        newConfig.put("backends", newBackends);

        if (backends != null) {
            Iterator<Map.Entry<String, Backend>> iter = backends.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Backend> e = iter.next();
                String key = e.getKey();
                Backend be = e.getValue();
                //clone be
                Backend newBe = new Backend();
                newBe.setName(be.getName());
                newBe.setMode(be.getMode());
                Map<String, Server> servers = be.getServers();

                if (servers != null) {
                    Map<String, Server> newServers = new HashMap<String, Server>();
                    Iterator<Map.Entry<String, Server>> serverIter = servers.entrySet().iterator();
                    while (serverIter.hasNext()) {
                        Map.Entry<String, Server> e2 = serverIter.next();
                        String name = e2.getKey();
                        Server server = e2.getValue();
                        Server newServer = new Server();
                        newServer.setName(server.getName());
                        newServer.setHost(server.getHost());
                        newServer.setPort(server.getPort());
                        newServer.setCheck(server.getCheck());
                        newServer.setCheckInterval(server.getCheckInterval());
                        newServer.setMaxconn(server.getMaxconn());
                        newServer.setWeight(server.getWeight());
                    }
                    be.setServers(newServers);
                }

                newBackends.put(key, newBe);
            }
        }


        return newConfig;
    }
}
