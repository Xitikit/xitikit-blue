package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Token data used for accessing the Azure B2C GraphAPI
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken{

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("scope")
  private String scope;

  @JsonProperty("expires_in")
  private Long expiresIn;

  @JsonProperty("ext_expires_in")
  private Long extExpiresIn;

  @JsonProperty("expires_on")
  private Long expiresOn;

  @JsonProperty("not_before")
  private Long notBefore;

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("resource")
  private String resource;

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

  public String getTokenType(){

    return tokenType;
  }

  public void setTokenType(final String tokenType){

    this.tokenType = tokenType;
  }

  public String getScope(){

    return scope;
  }

  public void setScope(final String scope){

    this.scope = scope;
  }

  public Long getExpiresIn(){

    return expiresIn;
  }

  public void setExpiresIn(final Long expiresIn){

    this.expiresIn = expiresIn;
  }

  public Long getExtExpiresIn(){

    return extExpiresIn;
  }

  public void setExtExpiresIn(final Long extExpiresIn){

    this.extExpiresIn = extExpiresIn;
  }

  public Long getExpiresOn(){

    return expiresOn;
  }

  public void setExpiresOn(final Long expiresOn){

    this.expiresOn = expiresOn;
  }

  public Long getNotBefore(){

    return notBefore;
  }

  public void setNotBefore(final Long notBefore){

    this.notBefore = notBefore;
  }

  public String getAccessToken(){

    return accessToken;
  }

  public void setAccessToken(final String accessToken){

    this.accessToken = accessToken;
  }

  public String getResource(){

    return resource;
  }

  public void setResource(final String resource){

    this.resource = resource;
  }
}
