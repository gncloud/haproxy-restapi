package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body2
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-26T15:15:22.594Z")

public class Body2   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("backend")
  private String backend = null;

  @JsonProperty("pattern")
  private String pattern = null;

  public Body2 name(String name) {
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

  public Body2 backend(String backend) {
    this.backend = backend;
    return this;
  }

   /**
   * Get backend
   * @return backend
  **/
  @ApiModelProperty(value = "")


  public String getBackend() {
    return backend;
  }

  public void setBackend(String backend) {
    this.backend = backend;
  }

  public Body2 pattern(String pattern) {
    this.pattern = pattern;
    return this;
  }

   /**
   * Get pattern
   * @return pattern
  **/
  @ApiModelProperty(value = "")


  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body2 body2 = (Body2) o;
    return Objects.equals(this.name, body2.name) &&
        Objects.equals(this.backend, body2.backend) &&
        Objects.equals(this.pattern, body2.pattern);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, backend, pattern);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body2 {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    backend: ").append(toIndentedString(backend)).append("\n");
    sb.append("    pattern: ").append(toIndentedString(pattern)).append("\n");
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

