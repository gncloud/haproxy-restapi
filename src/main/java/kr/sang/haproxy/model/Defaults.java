package kr.sang.haproxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Defaults
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

public class Defaults   {
  @JsonProperty("mode")
  private String mode = null;

  @JsonProperty("timeoutConnect")
  private String timeoutConnect = null;

  @JsonProperty("timeoutClient")
  private String timeoutClient = null;

  @JsonProperty("timeoutServer")
  private String timeoutServer = null;

  @JsonProperty("etc")
  private List<String> etc = null;

  public Defaults mode(String mode) {
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

  public Defaults timeoutConnect(String timeoutConnect) {
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

  public Defaults timeoutClient(String timeoutClient) {
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

  public Defaults timeoutServer(String timeoutServer) {
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

  public Defaults etc(List<String> etc) {
    this.etc = etc;
    return this;
  }

  public Defaults addEtcItem(String etcItem) {
    if (this.etc == null) {
      this.etc = new ArrayList<String>();
    }
    this.etc.add(etcItem);
    return this;
  }

   /**
   * Get etc
   * @return etc
  **/
  @ApiModelProperty(value = "")


  public List<String> getEtc() {
    return etc;
  }

  public void setEtc(List<String> etc) {
    this.etc = etc;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Defaults defaults = (Defaults) o;
    return Objects.equals(this.mode, defaults.mode) &&
        Objects.equals(this.timeoutConnect, defaults.timeoutConnect) &&
        Objects.equals(this.timeoutClient, defaults.timeoutClient) &&
        Objects.equals(this.timeoutServer, defaults.timeoutServer) &&
        Objects.equals(this.etc, defaults.etc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, timeoutConnect, timeoutClient, timeoutServer, etc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Defaults {\n");
    
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    timeoutConnect: ").append(toIndentedString(timeoutConnect)).append("\n");
    sb.append("    timeoutClient: ").append(toIndentedString(timeoutClient)).append("\n");
    sb.append("    timeoutServer: ").append(toIndentedString(timeoutServer)).append("\n");
    sb.append("    etc: ").append(toIndentedString(etc)).append("\n");
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

