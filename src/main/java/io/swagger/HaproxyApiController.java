package io.swagger;

import io.swagger.model.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    private Config config;
    private ReentrantReadWriteLock lock;

    @Autowired
    private ConfigFileHelper configFileHelper;

    @Autowired
    private ProxyHelper proxyHelper;


    public HaproxyApiController() {
        logger.info("new HaproxyApiController ");
        lock = new ReentrantReadWriteLock();
    }

    @PostConstruct
    public void init() {
        try {
            Map map = configFileHelper.loadObjectFile();
            proxyHelper.applyConfig(map);

            config = new Config(map);
        } catch (Exception e) {
            logger.error("bind error", e);
        }
    }

    @PreDestroy
    public void destroy() {
        logger.info("haproxy destroy");
        proxyHelper.stopProxy();
    }


    private void applyConfig(Config newConfig) {
        //1. 적용.
        Map configMap = newConfig.getMap();
        proxyHelper.applyConfig(configMap);
        //2. 메모리 객체 덤프
        try {
            File tempFile = configFileHelper.saveObjectFile(configMap);
            config = newConfig;
        } catch (IOException e) {
            logger.error("Cannot save memory file.", e);
            throw new RuntimeException("Cannot save memory file :" + e.getMessage());
        }

    }

    @Override
    public ResponseEntity<Map<String, Service>> getConfig() {
        ReentrantReadWriteLock.WriteLock readLock = lock.writeLock();
        readLock.lock();
        try {
            return new ResponseEntity<>(config.getMap(), HttpStatus.OK);
        } finally {
            readLock.unlock();
        }
    }


    @Override
    public ResponseEntity<Service> postService(@PathVariable("id") String id, @Valid @RequestBody Service service) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Config newConfig = config.cloneConfig();
            newConfig.addService(id, service);
            applyConfig(newConfig);
            return new ResponseEntity<>(service, HttpStatus.OK);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public ResponseEntity<Service> deleteService(@PathVariable("id") String id, @PathVariable("port") String port, @RequestParam(name = "acl", required = false) String acl) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Config newConfig = config.cloneConfig();
            Service service = config.removeService(id, port, acl);
            applyConfig(newConfig);

            return new ResponseEntity<>(service, HttpStatus.OK);
        } finally {
            writeLock.unlock();
        }
    }


}
