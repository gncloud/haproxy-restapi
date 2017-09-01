package io.swagger;

import io.swagger.model.Backend;
import io.swagger.model.Frontend;
import io.swagger.model.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by swsong on 17. 9. 1..
 */
public class ProxyHelperTest {

    public void testRenderTemplate() throws IOException {

        Map<String, Object> c = new HashMap<String, Object>();
        Map<String, Frontend> fes = new HashMap<String, Frontend>();
        c.put("frontends", fes);
        Map<String, Backend> bes = new HashMap<String, Backend>();
        c.put("backends", bes);

        Frontend fe = new Frontend();
        fe.setMode("http");
        fe.setBindIp("192.168.1.5");
        fe.setBindPort(8080L);
        fe.setDefaultBackend("node1");
        fes.put("12345", fe);


        Backend be = new Backend();
        be.setName("be1");
        be.setMode("tcp");
        Map servers = new HashMap<String, Server>();
        Server s1=new Server();
        s1.setName("node1");
        s1.setPort(9999L);
        s1.setHost("192.168.1.6");
        servers.put("node1", s1);
        be.setServers(servers);
        bes.put("be1", be);

        ProxyHelper helper = new ProxyHelper();
        String str = helper.renderTemplate(c);

        System.out.println(str);
    }
}
