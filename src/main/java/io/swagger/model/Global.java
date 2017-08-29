package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Options;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Global
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-29T05:51:56.514Z")

public class Global extends HashMap<String, Options>  {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("mode")
  private String mode = null;

  @JsonProperty("maxconn")
  private Long maxconn = null;

  public Global name(String name) {
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

  public Global mode(String mode) {
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

  public Global maxconn(Long maxconn) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Global global = (Global) o;
    return Objects.equals(this.name, global.name) &&
        Objects.equals(this.mode, global.mode) &&
        Objects.equals(this.maxconn, global.maxconn) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, mode, maxconn, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Global {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    maxconn: ").append(toIndentedString(maxconn)).append("\n");
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

