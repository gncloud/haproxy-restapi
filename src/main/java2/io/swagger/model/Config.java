package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Config
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-26T15:15:22.594Z")

public class Config   {
  @JsonProperty("global")
  private Object global = null;

  @JsonProperty("defaults")
  private Object defaults = null;

  @JsonProperty("frontends")
  private Object frontends = null;

  @JsonProperty("backends")
  private Object backends = null;

  public Config global(Object global) {
    this.global = global;
    return this;
  }

   /**
   * Get global
   * @return global
  **/
  @ApiModelProperty(value = "")


  public Object getGlobal() {
    return global;
  }

  public void setGlobal(Object global) {
    this.global = global;
  }

  public Config defaults(Object defaults) {
    this.defaults = defaults;
    return this;
  }

   /**
   * Get defaults
   * @return defaults
  **/
  @ApiModelProperty(value = "")


  public Object getDefaults() {
    return defaults;
  }

  public void setDefaults(Object defaults) {
    this.defaults = defaults;
  }

  public Config frontends(Object frontends) {
    this.frontends = frontends;
    return this;
  }

   /**
   * Get frontends
   * @return frontends
  **/
  @ApiModelProperty(value = "")


  public Object getFrontends() {
    return frontends;
  }

  public void setFrontends(Object frontends) {
    this.frontends = frontends;
  }

  public Config backends(Object backends) {
    this.backends = backends;
    return this;
  }

   /**
   * Get backends
   * @return backends
  **/
  @ApiModelProperty(value = "")


  public Object getBackends() {
    return backends;
  }

  public void setBackends(Object backends) {
    this.backends = backends;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Config config = (Config) o;
    return Objects.equals(this.global, config.global) &&
        Objects.equals(this.defaults, config.defaults) &&
        Objects.equals(this.frontends, config.frontends) &&
        Objects.equals(this.backends, config.backends);
  }

  @Override
  public int hashCode() {
    return Objects.hash(global, defaults, frontends, backends);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Config {\n");
    
    sb.append("    global: ").append(toIndentedString(global)).append("\n");
    sb.append("    defaults: ").append(toIndentedString(defaults)).append("\n");
    sb.append("    frontends: ").append(toIndentedString(frontends)).append("\n");
    sb.append("    backends: ").append(toIndentedString(backends)).append("\n");
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

