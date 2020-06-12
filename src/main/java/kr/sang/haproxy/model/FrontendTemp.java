package kr.sang.haproxy.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class FrontendTemp implements Serializable{
  private String name = null;

  private String mode = null;

  private Long bindPort = null;

  private String bindIp = null;

  private String defaultBackend = null;

  private String timeoutConnect = null;

  private String timeoutClient = null;

  private String timeoutServer = null;

  private List<ACL> acls = null;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public Long getBindPort() {
    return bindPort;
  }

  public void setBindPort(Long bindPort) {
    this.bindPort = bindPort;
  }

  public String getBindIp() {
    return bindIp;
  }

  public void setBindIp(String bindIp) {
    this.bindIp = bindIp;
  }

  public String getDefaultBackend() {
    return defaultBackend;
  }

  public void setDefaultBackend(String defaultBackend) {
    this.defaultBackend = defaultBackend;
  }

  public String getTimeoutConnect() {
    return timeoutConnect;
  }

  public void setTimeoutConnect(String timeoutConnect) {
    this.timeoutConnect = timeoutConnect;
  }

  public String getTimeoutClient() {
    return timeoutClient;
  }

  public void setTimeoutClient(String timeoutClient) {
    this.timeoutClient = timeoutClient;
  }

  public String getTimeoutServer() {
    return timeoutServer;
  }

  public void setTimeoutServer(String timeoutServer) {
    this.timeoutServer = timeoutServer;
  }

  public List<ACL> getAcls() {
    return acls;
  }

  public void setAcls(List<ACL> acls) {
    this.acls = acls;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FrontendTemp frontend = (FrontendTemp) o;
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
    sb.append("    acls: ").append(toIndentedString(acls)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

