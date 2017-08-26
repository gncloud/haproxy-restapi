package io.swagger.api;

import io.swagger.model.*;

import io.swagger.annotations.*;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-25T10:11:42.178Z")

@Controller
public class ConfigApiController implements ConfigApi {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ConfigApiController.class);


    private static Config config = new Config();

    public ConfigApiController() {
        //TODO 로딩.
        config = new Config();
    }


    public ResponseEntity<Void> deleteAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "ID of acl to return",required=true ) @PathVariable("aclId") String aclId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBackend(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteFrontend(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteServer(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "ID of server",required=true ) @PathVariable("serverId") String serverId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<ACL> getAcl(@ApiParam(value = "ID of frontend to return", required = true) @PathVariable("frontendId") String frontendId,
                                      @ApiParam(value = "ID of acl to return", required = true) @PathVariable("aclId") String aclId) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<List<ACL>> getAcls(@ApiParam(value = "ID of frontend to return", required = true) @PathVariable("frontendId") String frontendId) {
        // do some magic!
        return new ResponseEntity<List<ACL>>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> getBackend(@ApiParam(value = "ID of backend", required = true) @PathVariable("backendId") String backendId) {
        // do some magic!
        return new ResponseEntity<Backend>(HttpStatus.OK);
    }

    public ResponseEntity<List<Backend>> getBackends() {
        // do some magic!
        return new ResponseEntity<List<Backend>>(HttpStatus.OK);
    }

    public ResponseEntity<Config> getConfig() {
        // do some magic!
        return new ResponseEntity<Config>(config, HttpStatus.OK);
    }

    public ResponseEntity<Defaults> getDefaults() {
        // do some magic!
        return new ResponseEntity<Defaults>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> getFrontend(@ApiParam(value = "ID of frontend to return", required = true) @PathVariable("frontendId") String frontendId) {
        // do some magic!

        return new ResponseEntity<Frontend>(HttpStatus.OK);
    }

    public ResponseEntity<List<Object>> getFrontends() {
        // do some magic!
        return new ResponseEntity<List<Object>>(config.getFrontends(), HttpStatus.OK);
    }

    public ResponseEntity<Global> getGlobal() {
        // do some magic!
        return new ResponseEntity<Global>(HttpStatus.OK);
    }

    public ResponseEntity<Server> getServer(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "ID of server",required=true ) @PathVariable("serverId") String serverId) {
        // do some magic!
        return new ResponseEntity<Server>(HttpStatus.OK);
    }

    public ResponseEntity<List<Server>> getServers(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId) {
        // do some magic!
        return new ResponseEntity<List<Server>>(HttpStatus.OK);
    }

    public ResponseEntity<ACL> newAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "The acl to update." ,required=true )  @Valid @RequestBody Body1 body) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> newBackend(@ApiParam(value = "The backend to create." ,required=true )  @Valid @RequestBody Backend backend) {
        // do some magic!
        return new ResponseEntity<Backend>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> newFrontend(@ApiParam(value = "The frontend to create." ,required=true )  @Valid @RequestBody Frontend frontend) {
        // do some magic!
        boolean isUniq = true;
        logger.info("config.getFrontends() : {}", config.getFrontends());
        if(config.getFrontends() != null) {
            for (Object o : config.getFrontends()) {
                Frontend f = (Frontend) o;
                if (f.getName().equals(frontend.getName())) {
                    isUniq = false;
                    break;
                }
            }
        }

        if (isUniq) {
            config.addFrontendsItem(frontend);
        }
        return new ResponseEntity<Frontend>(frontend, HttpStatus.OK);
    }

    public ResponseEntity<Server> newServer(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "The server to add." ,required=true )  @Valid @RequestBody Body4 body) {
        // do some magic!
        return new ResponseEntity<Server>(HttpStatus.OK);
    }

    public ResponseEntity<Config> postConfig(@ApiParam(value = "The config to write.", required = true) @Valid @RequestBody Config1 config) {
        // do some magic!
        return new ResponseEntity<Config>(HttpStatus.OK);
    }

    public ResponseEntity<Config> putConfig(@ApiParam(value = "The config to update." ,required=true )  @Valid @RequestBody Config config) {
        // do some magic!
        return new ResponseEntity<Config>(HttpStatus.OK);
    }

    public ResponseEntity<Defaults> setDefaults(@ApiParam(value = "The defaults to set." ,required=true )  @Valid @RequestBody Defaults1 defaults) {
        // do some magic!
        return new ResponseEntity<Defaults>(HttpStatus.OK);
    }

    public ResponseEntity<Global> setGlobal(@ApiParam(value = "The global to set." ,required=true )  @Valid @RequestBody Global1 global) {
        // do some magic!
        return new ResponseEntity<Global>(HttpStatus.OK);
    }

    public ResponseEntity<ACL> updateAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "ID of acl to return",required=true ) @PathVariable("aclId") String aclId,
        @ApiParam(value = "The acl to update." ,required=true )  @Valid @RequestBody Body2 body) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> updateBackend(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "The backend to update." ,required=true )  @Valid @RequestBody Body3 body) {
        // do some magic!
        return new ResponseEntity<Backend>(HttpStatus.OK);
    }

    public ResponseEntity<Defaults> updateDefaults(@ApiParam(value = "The defaults to update." ,required=true )  @Valid @RequestBody Defaults defaults) {
        // do some magic!
        return new ResponseEntity<Defaults>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> updateFrontend(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "The frontend to update." ,required=true )  @Valid @RequestBody Body body) {
        // do some magic!
        return new ResponseEntity<Frontend>(HttpStatus.OK);
    }

    public ResponseEntity<Global> updateGlobal(@ApiParam(value = "The global to update." ,required=true )  @Valid @RequestBody Global global) {
        // do some magic!
        return new ResponseEntity<Global>(HttpStatus.OK);
    }

    public ResponseEntity<Server> updateServer(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "ID of server",required=true ) @PathVariable("serverId") String serverId,
        @ApiParam(value = "The backend to update." ,required=true )  @Valid @RequestBody Body5 body) {
        // do some magic!
        return new ResponseEntity<Server>(HttpStatus.OK);
    }

}
