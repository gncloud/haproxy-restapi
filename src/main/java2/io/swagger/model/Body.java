package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-26T15:15:22.594Z")

public class Body   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("mode")
  private String mode = null;

  @JsonProperty("bindPort")
  private Long bindPort = null;

  @JsonProperty("bindIp")
  private String bindIp = null;

  @JsonProperty("defaultBackend")
  private String defaultBackend = null;

  @JsonProperty("timeoutConnect")
  private String timeoutConnect = null;

  @JsonProperty("timeoutClient")
  private String timeoutClient = null;

  @JsonProperty("timeoutServer")
  private String timeoutServer = null;

  @JsonProperty("options")
  private Object options = null;

  @JsonProperty("acls")
  private List<Object> acls = null;

  @JsonProperty("httpSpikeLimit")
  private Object httpSpikeLimit = null;

  @JsonProperty("tcpSpikeLimit")
  private Object tcpSpikeLimit = null;

  public Body name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Body mode(String mode) {
    this.mode = mode;
    return this;
  }

   /**
   * Get mode
   * @return mode
  **/
  @ApiModelProperty(value = "")


  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public Body bindPort(Long bindPort) {
    this.bindPort = bindPort;
    return this;
  }

   /**
   * Get bindPort
   * @return bindPort
  **/
  @ApiModelProperty(value = "")


  public Long getBindPort() {
    return bindPort;
  }

  public void setBindPort(Long bindPort) {
    this.bindPort = bindPort;
  }

  public Body bindIp(String bindIp) {
    this.bindIp = bindIp;
    return this;
  }

   /**
   * Get bindIp
   * @return bindIp
  **/
  @ApiModelProperty(value = "")


  public String getBindIp() {
    return bindIp;
  }

  public void setBindIp(String bindIp) {
    this.bindIp = bindIp;
  }

  public Body defaultBackend(String defaultBackend) {
    this.defaultBackend = defaultBackend;
    return this;
  }

   /**
   * Get defaultBackend
   * @return defaultBackend
  **/
  @ApiModelProperty(value = "")


  public String getDefaultBackend() {
    return defaultBackend;
  }

  public void setDefaultBackend(String defaultBackend) {
    this.defaultBackend = defaultBackend;
  }

  public Body timeoutConnect(String timeoutConnect) {
    this.timeoutConnect = timeoutConnect;
    return this;
  }

   /**
   * Get timeoutConnect
   * @return timeoutConnect
  **/
  @ApiModelProperty(value = "")


  public String getTimeoutConnect() {
    return timeoutConnect;
  }

  public void setTimeoutConnect(String timeoutConnect) {
    this.timeoutConnect = timeoutConnect;
  }

  public Body timeoutClient(String timeoutClient) {
    this.timeoutClient = timeoutClient;
    return this;
  }

   /**
   * Get timeoutClient
   * @return timeoutClient
  **/
  @ApiModelProperty(value = "")


  public String getTimeoutClient() {
    return timeoutClient;
  }

  public void setTimeoutClient(String timeoutClient) {
    this.timeoutClient = timeoutClient;
  }

  public Body timeoutServer(String timeoutServer) {
    this.timeoutServer = timeoutServer;
    return this;
  }

   /**
   * Get timeoutServer
   * @return timeoutServer
  **/
  @ApiModelProperty(value = "")


  public String getTimeoutServer() {
    return timeoutServer;
  }

  public void setTimeoutServer(String timeoutServer) {
    this.timeoutServer = timeoutServer;
  }

  public Body options(Object options) {
    this.options = options;
    return this;
  }

   /**
   * Get options
   * @return options
  **/
  @ApiModelProperty(value = "")


  public Object getOptions() {
    return options;
  }

  public void setOptions(Object options) {
    this.options = options;
  }

  public Body acls(List<Object> acls) {
    this.acls = acls;
    return this;
  }

  public Body addAclsItem(Object aclsItem) {
    if (this.acls == null) {
      this.acls = new ArrayList<Object>();
    }
    this.acls.add(aclsItem);
    return this;
  }

   /**
   * Get acls
   * @return acls
  **/
  @ApiModelProperty(value = "")


  public List<Object> getAcls() {
    return acls;
  }

  public void setAcls(List<Object> acls) {
    this.acls = acls;
  }

  public Body httpSpikeLimit(Object httpSpikeLimit) {
    this.httpSpikeLimit = httpSpikeLimit;
    return this;
  }

   /**
   * Get httpSpikeLimit
   * @return httpSpikeLimit
  **/
  @ApiModelProperty(value = "")


  public Object getHttpSpikeLimit() {
    return httpSpikeLimit;
  }

  public void setHttpSpikeLimit(Object httpSpikeLimit) {
    this.httpSpikeLimit = httpSpikeLimit;
  }

  public Body tcpSpikeLimit(Object tcpSpikeLimit) {
    this.tcpSpikeLimit = tcpSpikeLimit;
    return this;
  }

   /**
   * Get tcpSpikeLimit
   * @return tcpSpikeLimit
  **/
  @ApiModelProperty(value = "")


  public Object getTcpSpikeLimit() {
    return tcpSpikeLimit;
  }

  public void setTcpSpikeLimit(Object tcpSpikeLimit) {
    this.tcpSpikeLimit = tcpSpikeLimit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body body = (Body) o;
    return Objects.equals(this.name, body.name) &&
        Objects.equals(this.mode, body.mode) &&
        Objects.equals(this.bindPort, body.bindPort) &&
        Objects.equals(this.bindIp, body.bindIp) &&
        Objects.equals(this.defaultBackend, body.defaultBackend) &&
        Objects.equals(this.timeoutConnect, body.timeoutConnect) &&
        Objects.equals(this.timeoutClient, body.timeoutClient) &&
        Objects.equals(this.timeoutServer, body.timeoutServer) &&
        Objects.equals(this.options, body.options) &&
        Objects.equals(this.acls, body.acls) &&
        Objects.equals(this.httpSpikeLimit, body.httpSpikeLimit) &&
        Objects.equals(this.tcpSpikeLimit, body.tcpSpikeLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, mode, bindPort, bindIp, defaultBackend, timeoutConnect, timeoutClient, timeoutServer, options, acls, httpSpikeLimit, tcpSpikeLimit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    bindPort: ").append(toIndentedString(bindPort)).append("\n");
    sb.append("    bindIp: ").append(toIndentedString(bindIp)).append("\n");
    sb.append("    defaultBackend: ").append(toIndentedString(defaultBackend)).append("\n");
    sb.append("    timeoutConnect: ").append(toIndentedString(timeoutConnect)).append("\n");
    sb.append("    timeoutClient: ").append(toIndentedString(timeoutClient)).append("\n");
    sb.append("    timeoutServer: ").append(toIndentedString(timeoutServer)).append("\n");
    sb.append("    options: ").append(toIndentedString(options)).append("\n");
    sb.append("    acls: ").append(toIndentedString(acls)).append("\n");
    sb.append("    httpSpikeLimit: ").append(toIndentedString(httpSpikeLimit)).append("\n");
    sb.append("    tcpSpikeLimit: ").append(toIndentedString(tcpSpikeLimit)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

