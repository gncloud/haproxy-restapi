package io.swagger;

import io.swagger.model.Service;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by swsong on 17. 9. 1..
 */
public class ProxyHelperTest {

    private void addService(Map<String, Service>  config, String name, Service service) {
        config.put(name, service);
    }

    @Test
    public void testSameHttpPort() throws IOException {

        Map<String, Service> config  = new HashMap<String, Service>();
        //String mode, Integer bindPort, String host, Integer port, Integer timeout, String subdomain
        addService(config, "service1", new Service("tcp", 9999, "192.168.1.50", 9999, 5000, null)); // ok
        addService(config, "service2", new Service("http", 8080, "192.168.1.51", 8080, 5000, null));
        addService(config, "service3", new Service("http", 8080, "192.168.1.52", 8080, 5000, null));
        addService(config, "service4", new Service("http", 8080, "192.168.1.53", 8080, 5000, null));

        ProxyHelper helper = new ProxyHelper();
        String str = helper.renderTemplate(config);

        System.out.println(str);
    }


    @Test
    public void testRenderTemplate() throws IOException {

        Map<String, Service> config  = new HashMap<String, Service>();

        //TODO service 여러개 만들어 보기..


        Service serviceOne = new Service();
        Service serviceTwo = new Service();
        Service serviceThree = new Service();
        Service serviceFour = new Service();
        Service service4 = new Service();
        Service service5 = new Service();


        // Case 1
        serviceOne.setMode("http");
        serviceOne.setBindPort(80);
        serviceOne.setHost("192.168.1.3");
        serviceOne.setPort(8080);
        serviceOne.setSubdomain("www");
        serviceOne.setTimeout(10000);

        // Case 2
        serviceTwo.setMode("tcp");
        serviceTwo.setBindPort(3306);
        serviceTwo.setHost("192.168.1.4");
        serviceTwo.setPort(3306);
        serviceTwo.setTimeout(50000);

        // Case 3
        serviceThree.setMode("http");
        serviceThree.setBindPort(8000);
        serviceThree.setHost("192.168.1.5");
        serviceThree.setPort(53);
        serviceThree.setSubdomain("");
        serviceThree.setTimeout(70000);


        // Case 3
        service4.setMode("http");
        service4.setBindPort(80);
        service4.setHost("192.168.1.5");
        service4.setPort(9000);
        service4.setSubdomain("blog");
        service4.setTimeout(70000);

        // Casr 4
        serviceFour.setMode("tcp");
        serviceFour.setBindPort(2222);
        serviceFour.setHost("192.168.1.6");
        serviceFour.setPort(22);
        serviceFour.setTimeout(6000);

        // Case 3
        service5.setMode("http");
        service5.setBindPort(8099);
        service5.setHost("192.168.1.7");
        service5.setPort(80);
//        service5.setSubdomain("");
        service5.setTimeout(70000);







        config.put("vm-1", serviceOne);
        config.put("vm-2", serviceTwo);
        config.put("vm-3", serviceThree);
        config.put("vm-3", service4);
        config.put("vm-4", serviceFour);
        config.put("vm-5", service5);

        ProxyHelper helper = new ProxyHelper();
        String str = helper.renderTemplate(config);

        System.out.println(str);
    }
}
