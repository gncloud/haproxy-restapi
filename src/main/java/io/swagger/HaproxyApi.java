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
    ResponseEntity<Map<String, Object>> getConfig();

    @RequestMapping(value = "/config/services/{id}",
            produces = { "application/json" },
            method = RequestMethod.POST)
            //instanceid와 port 를 조합하여 사용.
    //backend정보와 acl 패턴만 주면 자동생성.(http)
    //acl 이 없다면, default_backend로 설정. 대부분 tcp.
    ResponseEntity<Map<String, Object>> postService(String id, Service service);

    @RequestMapping(value = "/config/services/{id}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    //instance 고유아이디와 backend 정보만 주면.
    ResponseEntity<Map<String, Object>> deleteService();



}



