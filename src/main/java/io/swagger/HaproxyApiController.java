package io.swagger;

import io.swagger.annotations.ApiParam;
import io.swagger.api.ApiException;
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
import java.util.*;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

@RestController
public class HaproxyApiController implements ConfigApi {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(HaproxyApiController.class);

    @Autowired
    private ProxyHelper proxyHelper;

    @Autowired
    private TempleteEngine templeteEngine;

    @Autowired
    private HaproxyProcess haproxyProcess;


    private static Config config = new Config();

    public HaproxyApiController() {
        //TODO 로딩.
        config = new Config();
    }

    private Config cloneConfig() {
        Config c = new Config();
        Global global = config.getGlobal();
        Defaults defaults = config.getDefaults();
        Frontends frontends = config.getFrontends();
        Backends backends = config.getBackends();

        //global
        Global newGlobal = new Global();

        newGlobal.setMaxconn(global.getMaxconn());
        newGlobal.setEtc(cloneList(global.getEtc()));
        c.setGlobal(newGlobal);

        //defaults
        Defaults newDefaults = new Defaults();
        newDefaults.setMode(defaults.getMode());
        newDefaults.setTimeoutClient(defaults.getTimeoutClient());
        newDefaults.setTimeoutConnect(defaults.getTimeoutConnect());
        newDefaults.setTimeoutServer(defaults.getTimeoutServer());
        newDefaults.setEtc(cloneList(defaults.getEtc()));
        c.setDefaults(newDefaults);

        //frontends
        Frontends newFrontends = new Frontends();
        c.setFrontends(newFrontends);
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

                ACLs acls = fe.getAcls();
                SpikeLimit httpSpikeLimit = fe.getHttpSpikeLimit();
                SpikeLimit tcpSpikeLimit = fe.getTcpSpikeLimit();

                if(acls != null) {
                    ACLs newAcls = new ACLs();

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

                if(httpSpikeLimit != null) {
                    SpikeLimit newHttpSpikeLimit = new SpikeLimit();
                    newHttpSpikeLimit.setExpiryTime(httpSpikeLimit.getExpiryTime());
                    newHttpSpikeLimit.setSampleTime(httpSpikeLimit.getSampleTime());
                    newHttpSpikeLimit.setRate(httpSpikeLimit.getRate());
                    fe.setHttpSpikeLimit(newHttpSpikeLimit);
                }

                if(tcpSpikeLimit != null) {
                    SpikeLimit newTcpSpikeLimit = new SpikeLimit();
                    newTcpSpikeLimit.setExpiryTime(tcpSpikeLimit.getExpiryTime());
                    newTcpSpikeLimit.setSampleTime(tcpSpikeLimit.getSampleTime());
                    newTcpSpikeLimit.setRate(tcpSpikeLimit.getRate());
                    fe.setTcpSpikeLimit(newTcpSpikeLimit);
                }

                newFe.setEtc(cloneList(fe.getEtc()));

                newFrontends.put(key, newFe);
            }
        }


        //backends
        Backends newBackends = new Backends();
        c.setBackends(newBackends);
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

                newBe.setServers(be.getServers());
                Servers servers = be.getServers();

                if(servers != null) {
                    Servers newServers = new Servers();
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

                newBe.setEtc(cloneList(be.getEtc()));

                newBackends.put(key, newBe);
            }
        }


        return c;
    }

    private List<String> cloneList(List<String> list) {
        if(list == null) {
            return list;
        }
        List<String> newList = new ArrayList<String>();
        for(String e : list) {
            newList.add(e);
        }
        return newList;
    }

    public ResponseEntity<Config> configGet() {
        // do some magic!
        return new ResponseEntity<Config>(HttpStatus.OK);
    }

    public ResponseEntity<Config> configPost(@ApiParam(value = "The config to write." ,required=true )  @Valid @RequestBody Config config) {
        // do some magic!
        this.config = config;
        this.config.getGlobal().setMaxconn(100l);

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

    public ResponseEntity<ACL> newAcl(@ApiParam(value = "ID of frontend to return",required=true ) @PathVariable("frontendId") String frontendId,
        @ApiParam(value = "The acl to update." ,required=true )  @Valid @RequestBody ACL body) {
        // do some magic!
        return new ResponseEntity<ACL>(HttpStatus.OK);
    }

    public ResponseEntity<Backend> newBackend(@ApiParam(value = "The backend to create." ,required=true )  @Valid @RequestBody Backend backend) {
        // do some magic!
        return new ResponseEntity<Backend>(HttpStatus.OK);
    }

    public ResponseEntity<Frontend> newFrontend(@ApiParam(value = "The frontend to create." ,required=true )  @Valid @RequestBody Frontend frontend) throws ConfigInvalidException {
        // do some magic!
        Config c = cloneConfig();
        String name = frontend.getName();
        if(name == null) {
            throw new ResourceException(HttpStatus.BAD_REQUEST, "Name attribute is empty.");
        }
        Frontend oldFrontend = c.getFrontends().get(name);
        if(oldFrontend != null) {
            throw new ResourceException(HttpStatus.BAD_REQUEST, String.format("Frontend %s already exists.", name));
        }

        c.getFrontends().put(name, frontend);

        Map m = new HashMap();
        Map fes = new HashMap();
        m.put("frontends", fes);
        fes.put("a","1");
        fes.put("b","2");
        fes.put("c","3");
        proxyHelper.applyConfig(m);

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
        Config c = cloneConfig();
        Frontend fe = c.getFrontends().get(frontendId);
        if(fe == null) {
            throw new ResourceException(HttpStatus.BAD_REQUEST, String.format("Frontend %s is not found.", frontendId));
        }

        ACLs acls = fe.getAcls();

        ACL acl = acls.get(aclId);
        if(acl != null) {
            acls.put(aclId, body);
        } else {
            //acl not found.
//                return new ResponseEntity<ACL>(HttpStatus.NOT_FOUND);
            throw new ResourceException(HttpStatus.BAD_REQUEST, String.format("ACL %s is not found.", aclId));
        }

//        proxyHelper.applyConfig(c);

        return new ResponseEntity<ACL>(acl, HttpStatus.OK);
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
