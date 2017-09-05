package io.swagger.configuration;

import io.swagger.HaproxyRestServer;
import io.swagger.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

public class HaproxyFilter implements Filter{

    private Logger logger = LoggerFactory.getLogger(HaproxyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //isNotDev = (dev == null || "".equals(dev) || "false".equalsIgnoreCase(dev));
//        logger.info(dev);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        logger.info(dev);



//        if(isNotDev){
//            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//            String ip = req.getHeader("X-FORWARDED-FOR");
//            if (ip == null){
//                ip = req.getRemoteAddr();
//            }
//            List localIP = Arrays.asList("0:0:0:0:0:0:0:1", "127.0.0.1", "::1", "localhost");
//            logger.info("doFilter {}", ip);
//            if(!(ip.startsWith("172")
//                    || ip.startsWith("192")
//                    || localIP.contains(ip))){
//                throw new ServletException("Deny site access");
//            }
//        }

    }

    @Override
    public void destroy() {

    }
}
