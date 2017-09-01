package io.swagger;

import io.swagger.model.Backend;
import io.swagger.model.Config;
import io.swagger.model.Frontend;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by swsong on 17. 9. 1..
 */
public class ProxyHelperTest {

    @Test
    public void testRenderTemplate() throws IOException {

        Config c  = new Config();
        Map<String, Frontend> fes = new HashMap<String, Frontend>();
        Map<String, Backend> bes = new HashMap<String, Backend>();

        c.setFrontends(fes);
        c.setBackends(bes);

        Frontend fe = new Frontend();
        fe.setMode("http");
        fe.setBindIp("*");
        fe.setBindPort(8080);
        fe.setDefaultBackend("node1");
        fe.setAclBackend("be1");
        fe.setAclPattern("hdr_beg(host) www.");
        fes.put("service001", fe);

        Backend be = new Backend();
        be.setMode("http");
        be.setPort(9999);
        be.setHost("192.168.1.6");
        bes.put("service001", be);

        ProxyHelper helper = new ProxyHelper();
        String str = helper.renderTemplate(c);

        System.out.println(str);
    }
}
