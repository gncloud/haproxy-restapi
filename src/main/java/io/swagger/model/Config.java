package io.swagger.model;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Backends;
import io.swagger.model.Defaults;
import io.swagger.model.Frontends;
import io.swagger.model.Global;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Config
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

public class Config implements Serializable{
  @JsonProperty("global")
  private Global global = null;

  @JsonProperty("defaults")
  private Defaults defaults = null;

  @JsonProperty("frontends")
  private Frontends frontends = null;

  @JsonProperty("backends")
  private Backends backends = null;


  public Config(){
    this.global = new Global();
    this.defaults = new Defaults();
    this.frontends = new Frontends();
    this.backends = new Backends();
  }

  public Config global(Global global) {
    this.global = global;
    return this;
  }

   /**
   * Get global
   * @return global
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Global getGlobal() {
    return global;
  }

  public void setGlobal(Global global) {
    this.global = global;
  }

  public Config defaults(Defaults defaults) {
    this.defaults = defaults;
    return this;
  }

   /**
   * Get defaults
   * @return defaults
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Defaults getDefaults() {
    return defaults;
  }

  public void setDefaults(Defaults defaults) {
    this.defaults = defaults;
  }

  public Config frontends(Frontends frontends) {
    this.frontends = frontends;
    return this;
  }

   /**
   * Get frontends
   * @return frontends
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Frontends getFrontends() {
    return frontends;
  }

  public void setFrontends(Frontends frontends) {
    this.frontends = frontends;
  }

  public Config backends(Backends backends) {
    this.backends = backends;
    return this;
  }

   /**
   * Get backends
   * @return backends
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Backends getBackends() {
    return backends;
  }

  public void setBackends(Backends backends) {
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

