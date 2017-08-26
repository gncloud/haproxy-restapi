package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body5
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-26T15:15:22.594Z")

public class Body5   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("host")
  private String host = null;

  @JsonProperty("port")
  private Long port = null;

  @JsonProperty("weight")
  private Long weight = null;

  @JsonProperty("maxconn")
  private Long maxconn = null;

  @JsonProperty("check")
  private Boolean check = null;

  @JsonProperty("checkInterval")
  private Long checkInterval = null;

  public Body5 name(String name) {
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

  public Body5 host(String host) {
    this.host = host;
    return this;
  }

   /**
   * Get host
   * @return host
  **/
  @ApiModelProperty(value = "")


  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Body5 port(Long port) {
    this.port = port;
    return this;
  }

   /**
   * Get port
   * @return port
  **/
  @ApiModelProperty(value = "")


  public Long getPort() {
    return port;
  }

  public void setPort(Long port) {
    this.port = port;
  }

  public Body5 weight(Long weight) {
    this.weight = weight;
    return this;
  }

   /**
   * Get weight
   * @return weight
  **/
  @ApiModelProperty(value = "")


  public Long getWeight() {
    return weight;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
  }

  public Body5 maxconn(Long maxconn) {
    this.maxconn = maxconn;
    return this;
  }

   /**
   * Get maxconn
   * @return maxconn
  **/
  @ApiModelProperty(value = "")


  public Long getMaxconn() {
    return maxconn;
  }

  public void setMaxconn(Long maxconn) {
    this.maxconn = maxconn;
  }

  public Body5 check(Boolean check) {
    this.check = check;
    return this;
  }

   /**
   * Get check
   * @return check
  **/
  @ApiModelProperty(value = "")


  public Boolean getCheck() {
    return check;
  }

  public void setCheck(Boolean check) {
    this.check = check;
  }

  public Body5 checkInterval(Long checkInterval) {
    this.checkInterval = checkInterval;
    return this;
  }

   /**
   * Get checkInterval
   * @return checkInterval
  **/
  @ApiModelProperty(value = "")


  public Long getCheckInterval() {
    return checkInterval;
  }

  public void setCheckInterval(Long checkInterval) {
    this.checkInterval = checkInterval;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body5 body5 = (Body5) o;
    return Objects.equals(this.name, body5.name) &&
        Objects.equals(this.host, body5.host) &&
        Objects.equals(this.port, body5.port) &&
        Objects.equals(this.weight, body5.weight) &&
        Objects.equals(this.maxconn, body5.maxconn) &&
        Objects.equals(this.check, body5.check) &&
        Objects.equals(this.checkInterval, body5.checkInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, host, port, weight, maxconn, check, checkInterval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body5 {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    maxconn: ").append(toIndentedString(maxconn)).append("\n");
    sb.append("    check: ").append(toIndentedString(check)).append("\n");
    sb.append("    checkInterval: ").append(toIndentedString(checkInterval)).append("\n");
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

