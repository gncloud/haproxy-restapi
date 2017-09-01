import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestapiTest {

    @Test
    public void api(){

        Config config = new Config();

//      global
        Global global = config.getGlobal();
        global.setMaxconn(1000l);
        List<String> getc = new ArrayList<String>();
        getc.add("global etc 1");
        getc.add("global etc 2");
        getc.add("global etc 3");
        getc.add("global etc 4");
        global.setEtc(getc);

//      defaults
        Defaults defaults = config.getDefaults();
        defaults.setMode("http");
        defaults.setTimeoutClient("1000ms");
        defaults.setTimeoutConnect("5000ms");
        defaults.setTimeoutServer("100000ms");
        List<String> detc = new ArrayList<String>();
        detc.add("default etc 1");
        detc.add("default etc 2");
        detc.add("default etc 3");
        detc.add("default etc 4");
        defaults.setEtc(detc);


//      Frontend
        Frontend frontend = new Frontend();
//        frontend.setName("web80");
        frontend.setBindIp("127.0.0.1");
//        frontend.setBindPort(8080l);
        ACL acl = new ACL();
        acl.setName("acl1");
        acl.setBackend("back2");
        acl.setPattern("hdr_beg(host) www.");
        ACL acl2 = new ACL();
        acl2.setBackend("back2");
        acl2.setName("acl2");
        acl2.setPattern("hdr_beg(host) www.");

        ACLs acLs = new ACLs();
        acLs.put("acl1", acl);
        acLs.put("acl2", acl2);
//        frontend.setAcls(acLs);

        config.getFrontends().put("web80", frontend);



        System.out.println("config data: => ");
        System.out.print(config);

    }



}
