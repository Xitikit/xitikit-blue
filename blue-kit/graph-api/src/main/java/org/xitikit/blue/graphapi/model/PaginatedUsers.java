package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J. Keith Hoopes
 *   Copyright Xitikit.org 2017
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedUsers{

  private List<GraphApiUser> users = new ArrayList<>();

  @JsonProperty("odata.nextLink")
  private String nextLink;

  @JsonProperty("odata.metadata")
  private String metaData;

  @JsonIgnore
  public String getNextLinkToken(){

    if(StringUtils.isNotEmpty(nextLink)){
      int index = nextLink.indexOf("$skiptoken=");
      if(index != -1){
        return nextLink.substring(index + "$skiptoken=".length());
      }
      return null;
    }
    return null;
  }
}
