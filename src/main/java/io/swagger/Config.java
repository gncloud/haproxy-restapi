package io.swagger;

import io.swagger.model.Service;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by swsong on 17. 9. 4..
 */
public class Config {
    private static Logger logger = org.slf4j.LoggerFactory.getLogger(Config.class);

    private Map<String, Service> map;

    public Config() {
        map = new HashMap<>();

    }
    public Config(Map<String, Service> map) {
        this.map = map;
    }

    public Map<String, Service> getMap() {
        return map;
    }


    public void addService(String id, Service service) {
        isValidPort(service);
        String acl = service.getSubdomain();
        acl = (acl != null ? acl : "");
        String uid = id +"_" + service.getBindPort() + "_" + acl;
        logger.debug("ADD uid[{}] id[{}] port[{}]", uid, id, service.getBindPort());
        map.put(uid, service);
    }

    public Service removeService(String id, String port, String acl) {
        acl = (acl != null ? acl : "");
        String uid = id +"_" + port + "_" + acl;
        logger.debug("REMOVE uid[{}]", uid);
        return map.remove(uid);
    }


    private void isValidPort(Service service) {
        Iterator<Map.Entry<String, Service>> iter = map.entrySet().iterator();
        int newPort = service.getPort();
        String newSubdomain = service.getSubdomain();
        String mode = service.getMode();
        boolean isHttp = mode.equalsIgnoreCase("http");
        while(iter.hasNext()) {
            Map.Entry<String, Service> e = iter.next();
            Service oldService = e.getValue();
            int oldPort = oldService.getPort();
            String oldSubdomain = oldService.getSubdomain();
            if(oldPort == newPort) {
                //포트충돌. http가 아니면 설정을 받지 않는다.
                //http는 subdomain까지 동일하면 설정을 받지 않는다.
                if(isHttp) {
                    if(oldSubdomain == null) oldSubdomain = "";
                    if(newSubdomain == null) newSubdomain = "";
                    if(newSubdomain.equals(oldSubdomain)) {
                        throw new ConfigInvalidException("Frontend acl already exists : http/" + newPort+ "/" + newSubdomain);
                    }
                } else {
                    throw new ConfigInvalidException("Frontend bind port already exists : tcp/" + newPort);
                }
            }
        }
    }

    public Config cloneConfig() {
        Map<String, Service> newMap = new HashMap<>();
        Iterator<Map.Entry<String, Service>> iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Service> e = iter.next();
            String id = e.getKey();
            Service service =  e.getValue();
            Service newService = (Service) service.clone();
            newMap.put(id, newService);
        }
        return new Config(newMap);
    }


}
