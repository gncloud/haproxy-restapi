package io.swagger.api;

import io.swagger.model.ACL;
import io.swagger.model.ACLs;
import io.swagger.model.Backend;
import io.swagger.model.Backends;
import io.swagger.model.Config;
import io.swagger.model.Defaults;
import io.swagger.model.Frontend;
import io.swagger.model.Frontends;
import io.swagger.model.Global;
import io.swagger.model.Server;
import io.swagger.model.Servers;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")


public class ConfigApiController implements ConfigApi {



    public ResponseEntity<Config> configGet() {
        // do some magic!
        return new ResponseEntity<Config>(HttpStatus.OK);
    }

    public ResponseEntity<Config> configPost(@ApiParam(value = "The config to write." ,required=true )  @Valid @RequestBody Config config) {
        // do some magic!
        return new ResponseEntity<Config>(HttpStatus.OK);
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

    public ResponseEntity<ACL> newAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "The acl to update." ,required=true )  @Valid @RequestBody ACL body) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> newBackend(@ApiParam(value = "The backend to create." ,required=true )  @Valid @RequestBody Backend backend) {
        // do some magic!
        return new ResponseEntity<Backend>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> newFrontend(@ApiParam(value = "The frontend to create." ,required=true )  @Valid @RequestBody Frontend frontend) {
        // do some magic!
        return new ResponseEntity<Frontend>(HttpStatus.OK);
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
        return new ResponseEntity<Global>(HttpStatus.OK);
    }

    public ResponseEntity<ACL> updateAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "ID of acl to return",required=true ) @PathVariable("aclId") String aclId,
        @ApiParam(value = "The acl to update." ,required=true )  @Valid @RequestBody ACL body) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> updateBackend(@ApiParam(value = "ID of backend",required=true ) @PathVariable("backendId") String backendId,
        @ApiParam(value = "The backend to update." ,required=true )  @Valid @RequestBody Backend body) {
        // do some magic!
        return new ResponseEntity<Backend>(HttpStatus.OK);
    }

    public ResponseEntity<Backends> updateBackends(@ApiParam(value = "The backends to update." ,required=true )  @Valid @RequestBody Backends body) {
        // do some magic!
        return new ResponseEntity<Backends>(HttpStatus.OK);
    }

    public ResponseEntity<Defaults> updateDefaults(@ApiParam(value = "The defaults to update." ,required=true )  @Valid @RequestBody Defaults defaults) {
        // do some magic!
        return new ResponseEntity<Defaults>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> updateFrontend(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "The frontend to update." ,required=true )  @Valid @RequestBody Frontend body) {
        // do some magic!
        return new ResponseEntity<Frontend>(HttpStatus.OK);
    }

    public ResponseEntity<Frontends> updateFrontends(@ApiParam(value = "The frontends to update." ,required=true )  @Valid @RequestBody Frontends body) {
        // do some magic!
        return new ResponseEntity<Frontends>(HttpStatus.OK);
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
