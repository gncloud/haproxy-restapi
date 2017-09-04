package io.swagger.configuration;

import io.swagger.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null){
            ip = req.getRemoteAddr();
        }
        List localIP = Arrays.asList("0:0:0:0:0:0:0:1", "127.0.0.1", "::1", "localhost");
        logger.debug("doFilter {}", ip);
        if(!localIP.contains(ip)){
            throw new ServletException("Deny site access");
        }
    }

    @Override
    public void destroy() {

    }
}
