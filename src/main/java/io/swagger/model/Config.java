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
 * Config
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-25T10:11:42.178Z")

public class Config   {
  @JsonProperty("global")
  private Object global = null;

  @JsonProperty("defaults")
  private Object defaults = null;

  @JsonProperty("frontends")
  private List<Object> frontends = null;

  @JsonProperty("backends")
  private List<Object> backends = null;

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

  public Config frontends(List<Object> frontends) {
    this.frontends = frontends;
    return this;
  }

  public Config addFrontendsItem(Object frontendsItem) {
    if (this.frontends == null) {
      this.frontends = new ArrayList<Object>();
    }
    this.frontends.add(frontendsItem);
    return this;
  }

   /**
   * Get frontends
   * @return frontends
  **/
  @ApiModelProperty(value = "")


  public List<Object> getFrontends() {
    return frontends;
  }

  public void setFrontends(List<Object> frontends) {
    this.frontends = frontends;
  }

  public Config backends(List<Object> backends) {
    this.backends = backends;
    return this;
  }

  public Config addBackendsItem(Object backendsItem) {
    if (this.backends == null) {
      this.backends = new ArrayList<Object>();
    }
    this.backends.add(backendsItem);
    return this;
  }

   /**
   * Get backends
   * @return backends
  **/
  @ApiModelProperty(value = "")


  public List<Object> getBackends() {
    return backends;
  }

  public void setBackends(List<Object> backends) {
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

