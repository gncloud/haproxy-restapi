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

    private Map<String, Service> config;
    private ReentrantReadWriteLock lock;


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

    private void applyConfig(Map<String , Service> newConfig) {
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
    public ResponseEntity<Map<String, Service>> getConfig() {
        ReentrantReadWriteLock.WriteLock readLock = lock.writeLock();
        readLock.lock();
        try {
            return new ResponseEntity<>(config, HttpStatus.OK);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public ResponseEntity<Service> postService(@PathVariable("id") String id, @Valid @RequestBody Service service) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Map<String, Service> newConfig = cloneConfig();
            int port = service.getBindPort();
            String uid = id + "_" + port;
            newConfig.put(uid, service);
            applyConfig(newConfig);
            return new ResponseEntity<>(service, HttpStatus.OK);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public ResponseEntity<Service> deleteService(@PathVariable("id") String id, @PathVariable("port") String port) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Map<String, Service> newConfig = cloneConfig();
            String uid = id + "_" + port;
            Service service = newConfig.remove(uid);
            applyConfig(newConfig);

            return new ResponseEntity<>(service, HttpStatus.OK);
        } finally {
            writeLock.unlock();
        }
    }

    private Map<String, Service> cloneConfig() {
        Map<String, Service> newConfig = new HashMap<>();
        Iterator<Map.Entry<String, Service>> iter = newConfig.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Service> e = iter.next();
            String id = e.getKey();
            Service service =  e.getValue();
            Service newService = (Service) service.clone();
            newConfig.put(id, newService);
        }
        return newConfig;
    }
}
