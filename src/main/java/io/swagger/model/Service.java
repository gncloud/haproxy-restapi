package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by swsong on 17. 8. 31..
 */
public class Service implements Serializable, Cloneable {
        @JsonProperty("mode")
    private String mode;

    @JsonProperty("bindPort")
    private Integer bindPort;

    @JsonProperty("host")
    private String host;

    @JsonProperty("port")
    private Integer port;

    @JsonProperty("timeout")
    private Integer timeout;

    @JsonProperty("subdomain")
    private String subdomain;

    public Service() {
    }

    public Service(String mode, Integer bindPort, String host, Integer port, Integer timeout, String subdomain) {
        this.mode = mode;
        this.bindPort = bindPort;
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.subdomain = subdomain;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getBindPort() {
        return bindPort;
    }

    public void setBindPort(Integer bindPort) {
        this.bindPort = bindPort;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    @Override
    public Object clone() {
        return new Service(mode, bindPort, host, port, timeout, subdomain);
    }
}
