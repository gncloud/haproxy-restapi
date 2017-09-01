package io.swagger.model;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ACLs;
import io.swagger.model.SpikeLimit;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Frontend
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

public class Frontend implements Serializable{

  @JsonProperty("mode")
  private String mode = null;

  @JsonProperty("bindPort")
  private Integer bindPort = null;

  @JsonProperty("bindIp")
  private String bindIp = null;

  @JsonProperty("defaultBackend")
  private String defaultBackend = null;

  @JsonProperty("timeoutConnect")
  private Integer timeoutConnect = null;

  @JsonProperty("timeoutClient")
  private Integer timeoutClient = null;

  @JsonProperty("timeoutServer")
  private Integer timeoutServer = null;

  @JsonProperty("acls")
  private Map<String, ACL> acls = null;


  /**
   * Get name
   * @return name
   **/
  @ApiModelProperty(value = "")



  public Frontend mode(String mode) {
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

  public Frontend bindPort(Integer bindPort) {
    this.bindPort = bindPort;
    return this;
  }

  /**
   * Get bindPort
   * @return bindPort
   **/
  @ApiModelProperty(value = "")


  public Integer getBindPort() {
    return bindPort;
  }

  public void setBindPort(Integer bindPort) {
    this.bindPort = bindPort;
  }

  public Frontend bindIp(String bindIp) {
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

  public Frontend defaultBackend(String defaultBackend) {
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

  public Frontend timeoutConnect(Integer timeoutConnect) {
    this.timeoutConnect = timeoutConnect;
    return this;
  }

  /**
   * Get timeoutConnect
   * @return timeoutConnect
   **/
  @ApiModelProperty(value = "")


  public Integer getTimeoutConnect() {
    return timeoutConnect;
  }

  public void setTimeoutConnect(Integer timeoutConnect) {
    this.timeoutConnect = timeoutConnect;
  }

  public Frontend timeoutClient(Integer timeoutClient) {
    this.timeoutClient = timeoutClient;
    return this;
  }

  /**
   * Get timeoutClient
   * @return timeoutClient
   **/
  @ApiModelProperty(value = "")


  public Integer getTimeoutClient() {
    return timeoutClient;
  }

  public void setTimeoutClient(Integer timeoutClient) {
    this.timeoutClient = timeoutClient;
  }

  public Frontend timeoutServer(Integer timeoutServer) {
    this.timeoutServer = timeoutServer;
    return this;
  }

  /**
   * Get timeoutServer
   * @return timeoutServer
   **/
  @ApiModelProperty(value = "")


  public Integer getTimeoutServer() {
    return timeoutServer;
  }

  public void setTimeoutServer(Integer timeoutServer) {
    this.timeoutServer = timeoutServer;
  }

  public Frontend acls(ACLs acls) {
    this.acls = acls;
    return this;
  }



  /**
   * Get acls
   * @return acls
   **/
  @ApiModelProperty(value = "")

  @Valid

  public Map<String, ACL> getAcls() {
    return acls;
  }

  public Map<String, ACL> getAclsNotNull() {
    if(acls == null) {
      acls = new HashMap<>();
    }
    return acls;
  }


  public void setAcls(Map<String, ACL> acls) {
    this.acls = acls;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Frontend frontend = (Frontend) o;
    return Objects.equals(this.mode, frontend.mode) &&
            Objects.equals(this.bindPort, frontend.bindPort) &&
            Objects.equals(this.bindIp, frontend.bindIp) &&
            Objects.equals(this.defaultBackend, frontend.defaultBackend) &&
            Objects.equals(this.timeoutConnect, frontend.timeoutConnect) &&
            Objects.equals(this.timeoutClient, frontend.timeoutClient) &&
            Objects.equals(this.timeoutServer, frontend.timeoutServer) &&
            Objects.equals(this.acls, frontend.acls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, bindPort, bindIp, defaultBackend, timeoutConnect, timeoutClient, timeoutServer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Frontend {\n");

    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    bindPort: ").append(toIndentedString(bindPort)).append("\n");
    sb.append("    bindIp: ").append(toIndentedString(bindIp)).append("\n");
    sb.append("    defaultBackend: ").append(toIndentedString(defaultBackend)).append("\n");
    sb.append("    timeoutConnect: ").append(toIndentedString(timeoutConnect)).append("\n");
    sb.append("    timeoutClient: ").append(toIndentedString(timeoutClient)).append("\n");
    sb.append("    timeoutServer: ").append(toIndentedString(timeoutServer)).append("\n");
    sb.append("    acls: ").append(toIndentedString(acls)).append("\n");
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

