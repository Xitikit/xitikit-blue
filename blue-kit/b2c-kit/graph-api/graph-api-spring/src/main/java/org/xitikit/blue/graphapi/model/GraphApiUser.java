package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author J. Keith Hoopes
 *   Copyright Xitikit.org 2017
 *
 *   Models the request to update an Azure B2C user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphApiUser{

  @JsonProperty("objectId")
  private String id;

  private Boolean accountEnabled;

  private PasswordProfile PasswordProfile;

  private List<SignInName> signInNames;

  private String surname;

  private String displayName;

  private String givenName;

  @JsonProperty("userPrincipalName")
  private String userPrincipalName;

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
  public String getSignInEmail(){

    String email = "";
    if(signInNames != null){
      for(SignInName signInName : signInNames){
        if(signInName
             .getType()
             .equals("emailAddress")){
          email = signInName.getValue();
          break;
        }
      }
    }
    return email;
  }

  @JsonIgnore
  public void setSignInEmail(final String signInEmail){

    if(signInNames == null){
      signInNames = new ArrayList<>();
      signInNames.add(new SignInName("emailAddress", signInEmail));
      return;
    }

    for(SignInName signInName : signInNames){
      if(signInName
           .getType()
           .equals("emailAddress")){
        signInName.setValue(signInEmail);
        break;
      }
    }
  }
}