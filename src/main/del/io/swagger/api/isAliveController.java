package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;



@RestController
public class isAliveController {

    @RequestMapping(value = "/isAlive")
     public String isAlive(){
        return "statis : ok";
    }


}
