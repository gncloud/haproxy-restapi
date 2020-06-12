package io.swagger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by swsong on 17. 8. 31..
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResourceException.class)
    private ResponseEntity<Map> handleException(ResourceException e) {
        // log exception
        Map map = new HashMap<String, String>();
        map.put("message", e.getMessage());
        map.put("status", e.getHttpStatus().value());
        map.put("error", e.getHttpStatus().getReasonPhrase());
        return ResponseEntity.status(e.getHttpStatus()).body(map);
    }

    @ExceptionHandler(ConfigInvalidException.class)
    private ResponseEntity<Map> handleException(ConfigInvalidException e) {
        // log exception
        Map map = new HashMap<String, String>();
        map.put("message", e.getMessage());
        map.put("status", HttpStatus.BAD_REQUEST.value());
        map.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
}
