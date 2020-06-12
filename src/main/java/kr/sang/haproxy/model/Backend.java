package kr.sang.haproxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Backend
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

public class Backend  implements Serializable {

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("mode")
  private String mode = null;

  @JsonProperty("host")
  private String host = null;

  @JsonProperty("port")
  private int port;

  @JsonProperty("timeoutConnect")
  private Integer timeoutConnect = null;

  @JsonProperty("timeoutServer")
  private Integer timeoutServer = null;

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

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public Integer getTimeoutConnect() {
    return timeoutConnect;
  }

  public void setTimeoutConnect(Integer timeoutConnect) {
    this.timeoutConnect = timeoutConnect;
  }

  public Integer getTimeoutServer() {
    return timeoutServer;
  }

  public void setTimeoutServer(Integer timeoutServer) {
    this.timeoutServer = timeoutServer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Backend backend = (Backend) o;
    return Objects.equals(this.host, backend.host) &&
        Objects.equals(this.port, backend.port) &&
        Objects.equals(this.mode, backend.mode) &&
        Objects.equals(this.timeoutConnect, backend.timeoutConnect) &&
        Objects.equals(this.timeoutServer, backend.timeoutServer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, host, port);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Backend {\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    serverHost: ").append(toIndentedString(host)).append("\n");
    sb.append("    serverPort: ").append(toIndentedString(port)).append("\n");
    sb.append("    timeoutConnect: ").append(toIndentedString(timeoutConnect)).append("\n");
    sb.append("    timeoutServer: ").append(toIndentedString(timeoutServer)).append("\n");
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

