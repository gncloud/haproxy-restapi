package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Global1
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-26T15:15:22.594Z")

public class Global1   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("mode")
  private String mode = null;

  @JsonProperty("option")
  private Object option = null;

  @JsonProperty("maxconn")
  private Long maxconn = null;

  public Global1 name(String name) {
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

  public Global1 mode(String mode) {
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

  public Global1 option(Object option) {
    this.option = option;
    return this;
  }

   /**
   * Get option
   * @return option
  **/
  @ApiModelProperty(value = "")


  public Object getOption() {
    return option;
  }

  public void setOption(Object option) {
    this.option = option;
  }

  public Global1 maxconn(Long maxconn) {
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
    Global1 global1 = (Global1) o;
    return Objects.equals(this.name, global1.name) &&
        Objects.equals(this.mode, global1.mode) &&
        Objects.equals(this.option, global1.option) &&
        Objects.equals(this.maxconn, global1.maxconn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, mode, option, maxconn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Global1 {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    option: ").append(toIndentedString(option)).append("\n");
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

