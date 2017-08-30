package io.swagger;

import io.swagger.annotations.ApiParam;
import io.swagger.api.ConfigApi;
import io.swagger.freemarker.TempleteEngine;
import io.swagger.model.*;
import io.swagger.process.HaproxyProcess;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-29T05:33:12.063Z")

@RestController
public class HaproxyApiController implements ConfigApi {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(HaproxyApiController.class);

    @Autowired
    private TempleteEngine templeteEngine;

    @Autowired
    private HaproxyProcess haproxyProcess;


    private static Config config = new Config();

    public HaproxyApiController() {
        //TODO 로딩.
        config = new Config();
    }

    public ResponseEntity<Config> configGet() {
        // do some magic!
        return new ResponseEntity<Config>(config, HttpStatus.OK);
    }

    public ResponseEntity<Config> configPost(@ApiParam(value = "The config to write." ,required=true )  @Valid @RequestBody Config config) {
        // do some magic!
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        this.config = config;

        this.config.getGlobal().setMaxconn(100l);
        this.config.getGlobal().setName("test");
        this.config.getGlobal().setMode("aaaa");

        Options options = new Options();
        options.add(config.getGlobal());

        this.config.getGlobal().put("demon",options);


        return new ResponseEntity<Config>(this.config, HttpStatus.OK);
    }

    public ResponseEntity<Config> configPut(@ApiParam(value = "The config to update." ,required=true )  @Valid @RequestBody Config config) {
        // do some magic!
        return new ResponseEntity<Config>(HttpStatus.OK);
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

    public ResponseEntity<ACL> getAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "ID of acl to return",required=true ) @PathVariable("aclId") String aclId) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<ACLs> getAcls(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId) {
        // do some magic!
        return new ResponseEntity<ACLs>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> getBackend(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId) {
        // do some magic!
        return new ResponseEntity<Backend>(HttpStatus.OK);
    }

    public ResponseEntity<Backends> getBackends() {
        // do some magic!
        return new ResponseEntity<Backends>(HttpStatus.OK);
    }

    public ResponseEntity<Defaults> getDefaults() {
        // do some magic!
        return new ResponseEntity<Defaults>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> getFrontend(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId) {
        // do some magic!
        return new ResponseEntity<Frontend>(HttpStatus.OK);
    }

    public ResponseEntity<Frontends> getFrontends() {
        // do some magic!
        return new ResponseEntity<Frontends>(HttpStatus.OK);
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

    public ResponseEntity<Servers> getServers(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId) {
        // do some magic!
        return new ResponseEntity<Servers>(HttpStatus.OK);
    }

    public ResponseEntity<ACL> newAcl(@ApiParam(value = "ID of frontend to return", required = true) @PathVariable("frontendId") String frontendId,
                                      @ApiParam(value = "The acl to update.", required = true) @Valid @RequestBody ACL body) {
        // do some magic!
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> newBackend(@ApiParam(value = "The backend to create.", required = true) @Valid @RequestBody Backend backend) {
        // do some magic!
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> newFrontend(@ApiParam(value = "The frontend to create." ,required=true )  @Valid @RequestBody Frontend frontend) {
        // do some magic!
        boolean isUniq = true;
        logger.info("config.getFrontends() : {}", config.getFrontends());
        if(config.getFrontends() != null) {
            Iterator<Map.Entry<String,Frontend>> iter = config.getFrontends().entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry<String,Frontend> e = iter.next();
                String key = e.getKey();
                Frontend f = (Frontend) e.getValue();
                config.getFrontends().put(frontend.getName(), frontend);
            }
        }

        return new ResponseEntity<Frontend>(frontend, HttpStatus.OK);
    }

    public ResponseEntity<Server> newServer(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "The server to add." ,required=true )  @Valid @RequestBody Server body) {
        // do some magic!
        return new ResponseEntity<Server>(HttpStatus.OK);
    }

    public ResponseEntity<Defaults> setDefaults(@ApiParam(value = "The defaults to set." ,required=true )  @Valid @RequestBody Defaults defaults) {
        // do some magic!
        return new ResponseEntity<Defaults>(HttpStatus.OK);
    }

    public ResponseEntity<Global> setGlobal(@ApiParam(value = "The global to set." ,required=true )  @Valid @RequestBody Global global) {
        // do some magic!

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Options options = new Options();
        options.add(global);
        //config.global(global);
        config = config.global(global);

        try {
            templeteEngine.dataWrite(this.config);
        } catch (IOException e) {

        }
        Config config1 = templeteEngine.dateReader();


        System.out.println(config1);

        return new ResponseEntity<Global>(global, HttpStatus.OK);
    }

    public ResponseEntity<ACL> updateAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "ID of acl to return",required=true ) @PathVariable("aclId") String aclId,
        @ApiParam(value = "The acl to update." ,required=true )  @Valid @RequestBody ACL body) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> updateBackend(@ApiParam(value = "ID of backend", required = true) @PathVariable("backendId") String backendId,
                                                 @ApiParam(value = "The backend to update.", required = true) @Valid @RequestBody Backend body) {
        // do some magic!
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Backends> updateBackends(@ApiParam(value = "The backends to update.", required = true) @Valid @RequestBody Backends body) {
        // do some magic!
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Defaults> updateDefaults(@ApiParam(value = "The defaults to update." ,required=true )  @Valid @RequestBody Defaults defaults) {
        // do some magic!
        return new ResponseEntity<Defaults>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> updateFrontend(@ApiParam(value = "ID of frontend to return", required = true) @PathVariable("frontendId") String frontendId,
                                                   @ApiParam(value = "The frontend to update.", required = true) @Valid @RequestBody Frontend body) {
        // do some magic!
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Frontends> updateFrontends(@ApiParam(value = "The frontends to update.", required = true) @Valid @RequestBody Frontends body) {
        // do some magic!
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Global> updateGlobal(@ApiParam(value = "The global to update." ,required=true )  @Valid @RequestBody Global global) {
        // do some magic!
        return new ResponseEntity<Global>(HttpStatus.OK);
    }

    public ResponseEntity<Server> updateServer(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "ID of server",required=true ) @PathVariable("serverId") String serverId,
        @ApiParam(value = "The backend to update." ,required=true )  @Valid @RequestBody Server body) {
        // do some magic!
        return new ResponseEntity<Server>(HttpStatus.OK);
    }

}
