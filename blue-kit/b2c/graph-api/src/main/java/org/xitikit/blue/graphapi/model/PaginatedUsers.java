package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J. Keith Hoopes
 *   Copyright Xitikit.org 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginatedUsers{

  private List<GraphApiUser> users = new ArrayList<>();

  @JsonProperty("odata.nextLink")
  private String nextLink;

  @JsonProperty("odata.metadata")
  private String metaData;

  public List<GraphApiUser> getUsers(){

    return users;
  }

  public void setUsers(final List<GraphApiUser> users){

    this.users = users;
  }

  public String getNextLink(){

    return nextLink;
  }

  public void setNextLink(final String nextLink){

    this.nextLink = nextLink;
  }

  public String getMetaData(){

    return metaData;
  }

  public void setMetaData(final String metaData){

    this.metaData = metaData;
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
