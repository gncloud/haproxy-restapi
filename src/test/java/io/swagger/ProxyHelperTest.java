package io.swagger;

import io.swagger.model.Service;
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

        Map<String, Service> config  = new HashMap<String, Service>();

        //TODO service 여러개 만들어 보기..


        Service serviceOne = new Service();
        Service serviceTwo = new Service();
        Service serviceThree = new Service();
        Service serviceFour = new Service();



        // Case 1
        serviceOne.setMode("http");
        serviceOne.setPort(80);
        serviceOne.setBindPort(8080);
        serviceOne.setHost("*");
        serviceOne.setSubdomain("www");
        serviceOne.setTimeout(500);

        // Case 2
        serviceTwo.setMode("tcp");
        serviceTwo.setPort(22);
        serviceTwo.setBindPort(2222);
        serviceTwo.setHost("*");
        serviceTwo.setSubdomain("tcp");
        serviceTwo.setTimeout(6000);

        // Case 3
        serviceThree.setMode("http");
        serviceThree.setPort(53);
        serviceThree.setBindPort(5553);
        serviceThree.setHost("127.0.0.1");
        serviceThree.setSubdomain("blog");
        serviceThree.setTimeout(70000);

        // Casr 4
        serviceFour.setMode("tcp");
        serviceFour.setPort(22);
        serviceFour.setBindPort(2222);
        serviceThree.setSubdomain("");
        serviceFour.setHost("*");
        serviceFour.setTimeout(6000);





        config.put("case1", serviceOne);
        config.put("case2", serviceTwo);
        config.put("case3", serviceThree);
        config.put("case4", serviceFour);

        ProxyHelper helper = new ProxyHelper();
        String str = helper.renderTemplate(config);

        System.out.println(str);
    }
}
