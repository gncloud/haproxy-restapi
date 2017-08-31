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
 * Global
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-30T06:16:08.273Z")

public class Global   {

  @JsonProperty("maxconn")
  private Long maxconn = null;

  @JsonProperty("etc")
  private List<String> etc = null;

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

  public Global etc(List<String> etc) {
    this.etc = etc;
    return this;
  }

  public Global addEtcItem(String etcItem) {
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Global global = (Global) o;
    return Objects.equals(this.maxconn, global.maxconn) &&
        Objects.equals(this.etc, global.etc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxconn, etc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Global {\n");
    
    sb.append("    maxconn: ").append(toIndentedString(maxconn)).append("\n");
    sb.append("    etc: ").append(toIndentedString(etc)).append("\n");
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

