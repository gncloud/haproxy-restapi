package io.swagger;

import io.swagger.model.*;
import io.swagger.process.HaproxyRuntime;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by swsong on 17. 8. 31..
 */

@RestController
public class HaproxyApiController implements HaproxyApi {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(HaproxyApiController.class);


    Map<String, Object> config;

    public HaproxyApiController(){
        //TODO load
        config = new HashMap<>();
    }

    private void loadConfig() {

    }

    private void saveConfig() {

    }


    @Override
    public ResponseEntity<Map<String, Object>> getConfig() {

        return new ResponseEntity<Map<String, Object>>(config, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> postService(@PathVariable("id") String id, @Valid @RequestBody Service service) {
        logger.info("service={}", service);

        Map<String, Object> newConfig = cloneConfig();
        Map<String, Frontend> frontends = (Map<String, Frontend>) newConfig.get("frontends");
        Map<String, Backend> backends = (Map<String, Backend>) newConfig.get("backends");

        Frontend frontend = service.getFrontend();
        if(frontend != null) {
            String name = frontend.getName();
            if(name == null) {

                //ERROR
            }
            Frontend oldFrontend = frontends.get(name);
            if(oldFrontend != null) {
                //merge oldFrontend if http

                //error if tcp
            } else {
                frontends.put(name, frontend);
            }
        }

        Backend backend = service.getBackend();
        if(backend != null) {
            String name = backend.getName();
            if(name == null) {

                //ERROR
            }
            Backend oldBackend = backends.get(name);
            if(oldBackend != null) {
                //error
                logger.warn("backend replaced : {}", name);
            }
            backends.put(name,backend);
        }

        config = newConfig;

        //TODO 적용.

        return new ResponseEntity<Map<String, Object>>(config, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteService() {
//        Frontend frontend = service.getFrontend();
//        if(frontend != null) {
        return null;
    }

    private Map<String, Object> cloneConfig() {

        Map<String, Frontend> frontends = (Map<String, Frontend>) config.get("frontends");
        Map<String, Backend> backends = (Map<String, Backend>) config.get("backends");

        Map<String, Object> newConfig = new HashMap<>();

        //frontends
        Map<String, Frontend> newFrontends = new HashMap<String, Frontend>();
        newConfig.put("frontends", newFrontends);

        if(frontends != null) {
            Iterator<Map.Entry<String, Frontend>> iter = frontends.entrySet().iterator();
            while(iter.hasNext()) {
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

                Map<String, ACL> acls = fe.getAcls();

                if(acls != null) {
                    Map<String, ACL> newAcls = new HashMap<String, ACL>();

                    Iterator<Map.Entry<String, ACL>> aclIter = acls.entrySet().iterator();
                    while(aclIter.hasNext()) {
                        Map.Entry<String, ACL> e2 = aclIter.next();
                        String name = e2.getKey();
                        ACL acl = e2.getValue();
                        ACL newAcl = new ACL();
                        newAcl.setName(acl.getName());
                        newAcl.setPattern(acl.getPattern());
                        newAcl.setBackend(acl.getBackend());
                        newAcls.put(name, newAcl);
                    }
                    fe.setAcls(newAcls);
                }

                newFrontends.put(key, newFe);
            }
        }


        //backends
        Map<String, Backend> newBackends = new HashMap<String, Backend> ();
        newConfig.put("backends", newBackends);

        if(backends != null) {
            Iterator<Map.Entry<String, Backend>> iter = backends.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<String, Backend> e = iter.next();
                String key = e.getKey();
                Backend be = e.getValue();
                //clone be
                Backend newBe = new Backend();
                newBe.setName(be.getName());
                newBe.setMode(be.getMode());
                Map<String, Server> servers = be.getServers();

                if(servers != null) {
                    Map<String, Server> newServers = new HashMap<String, Server>();
                    Iterator<Map.Entry<String, Server>> serverIter = servers.entrySet().iterator();
                    while(serverIter.hasNext()) {
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
