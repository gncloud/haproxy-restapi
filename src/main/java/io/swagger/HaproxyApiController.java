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
import java.io.IOException;
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

    private Map<String, Object> config;
    private ReentrantReadWriteLock lock;

    @Autowired
    private ConfigFileHelper configFileHelper;

    @Autowired
    private ProxyHelper proxyHelper;


    public HaproxyApiController() {
        logger.info("new HaproxyApiController ");
        try {
            configFileHelper = new ConfigFileHelper();
            config = configFileHelper.loadObjectFile();
        } catch (Exception e) {
            logger.error("bind error", e);
        }
        lock = new ReentrantReadWriteLock();
    }

    private void applyConfig(Map<String, Object> newConfig) {
        //1. 적용.
        proxyHelper.applyConfig(newConfig);
        //2. 메모리 객체 덤프
        try {
            File tempFile = configFileHelper.saveObjectFile(newConfig);
        } catch (IOException e) {
            logger.error("Cannot save memory file.", e);
        }
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

            frontends.put(id, service.getFrontend());
            backends.put(id, service.getBackend());

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
                newBe.setMode(be.getMode());
                newBe.setHost(be.getHost());
                newBe.setPort(be.getPort());
                newBackends.put(key, newBe);
            }
        }


        return newConfig;
    }
}
