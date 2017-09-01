package io.swagger;

import io.swagger.model.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by swsong on 17. 8. 31..
 */
public interface HaproxyApi {

    @RequestMapping(value = "/config",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Map<String, Service>> getConfig();

    @RequestMapping(value = "/config/services/{id}",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Service> postService(String id, Service service);

    @RequestMapping(value = "/config/services/{id}/ports/{port}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Service> deleteService(String id, String port);



}



