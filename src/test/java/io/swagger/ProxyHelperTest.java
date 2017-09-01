package io.swagger;

import io.swagger.model.Backend;
import io.swagger.model.Config;
import io.swagger.model.Frontend;
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

        ProxyHelper helper = new ProxyHelper();
        String str = helper.renderTemplate(config);

        System.out.println(str);
    }
}
