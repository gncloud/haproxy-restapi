package io.swagger.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ACLs;
import io.swagger.model.SpikeLimit;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Frontend
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

public class Frontend implements Serializable{
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

  @JsonProperty("acls")
  private Map<String, ACL> acls = null;

  @JsonProperty("aclPattern")
  private String aclPattern = null;

  @JsonProperty("aclBackend")
  private String aclBackend = null;


  public Frontend name(String name) {
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

  public Frontend bindPort(Long bindPort) {
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

  public Frontend timeoutConnect(String timeoutConnect) {
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

  public Frontend timeoutClient(String timeoutClient) {
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

  public Frontend timeoutServer(String timeoutServer) {
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

  public Frontend acls(ACLs acls) {
    this.acls = acls;
    return this;
  }


  @ApiModelProperty(value = "")
  public String getAclBackend() {
    return aclBackend;
  }

  public void setAclBackend(String aclBackend) {
    this.aclBackend = aclBackend;
  }

  @ApiModelProperty(value = "")
  public String getAclPattern() {
    return aclPattern;
  }

  public void setAclPattern(String aclPattern) {
    this.aclPattern = aclPattern;
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
    return Objects.equals(this.name, frontend.name) &&
            Objects.equals(this.mode, frontend.mode) &&
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
    return Objects.hash(name, mode, bindPort, bindIp, defaultBackend, timeoutConnect, timeoutClient, timeoutServer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Frontend {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    bindPort: ").append(toIndentedString(bindPort)).append("\n");
    sb.append("    bindIp: ").append(toIndentedString(bindIp)).append("\n");
    sb.append("    defaultBackend: ").append(toIndentedString(defaultBackend)).append("\n");
    sb.append("    timeoutConnect: ").append(toIndentedString(timeoutConnect)).append("\n");
    sb.append("    timeoutClient: ").append(toIndentedString(timeoutClient)).append("\n");
    sb.append("    timeoutServer: ").append(toIndentedString(timeoutServer)).append("\n");
    sb.append("    aclBackend: ").append(toIndentedString(aclBackend)).append("\n");
    sb.append("    aclPattern: ").append(toIndentedString(aclPattern)).append("\n");
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

