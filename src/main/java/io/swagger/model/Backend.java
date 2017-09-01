package io.swagger.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Servers;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Backend backend = (Backend) o;
    return Objects.equals(this.host, backend.host) &&
        Objects.equals(this.port, backend.port) &&
        Objects.equals(this.mode, backend.mode);
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

