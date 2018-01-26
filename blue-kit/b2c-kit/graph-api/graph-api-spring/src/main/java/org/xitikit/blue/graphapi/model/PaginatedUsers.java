package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author J. Keith Hoopes
 *   Copyright Xitikit.org 2017
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginatedUsers{

  private List<GraphApiUser> users = new ArrayList<>();

  @JsonProperty("odata.nextLink")
  private String nextLink;

  @JsonProperty("odata.metadata")
  private String metaData;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties(){

    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(final String name, final Object value){

    this.additionalProperties.put(name, value);
  }

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
