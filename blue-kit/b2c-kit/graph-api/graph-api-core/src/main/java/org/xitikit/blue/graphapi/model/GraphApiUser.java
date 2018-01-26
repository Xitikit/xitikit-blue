package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.*;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GraphApiUser{

  @JsonProperty("objectId")
  private String id;

  private Boolean accountEnabled;

  private PasswordProfile passwordProfile;

  private List<SignInName> signInNames;

  private String surname;

  private String displayName;

  private String givenName;

  @JsonProperty("userPrincipalName")
  private String userPrincipalName;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  // CONSTRUCTORS

  public GraphApiUser(){

  }

  public GraphApiUser(final String id, final Boolean accountEnabled, final org.xitikit.blue.graphapi.model.PasswordProfile passwordProfile, final List<SignInName> signInNames, final String surname, final String displayName, final String givenName, final String userPrincipalName){

    this.id = id;
    this.accountEnabled = accountEnabled;
    this.passwordProfile = passwordProfile;
    this.signInNames = signInNames;
    this.surname = surname;
    this.displayName = displayName;
    this.givenName = givenName;
    this.userPrincipalName = userPrincipalName;
  }

  // GETTERS AND SETTERS

  public String getId(){

    return id;
  }

  public void setId(final String id){

    this.id = id;
  }

  public Boolean getAccountEnabled(){

    return accountEnabled;
  }

  public void setAccountEnabled(final Boolean accountEnabled){

    this.accountEnabled = accountEnabled;
  }

  public org.xitikit.blue.graphapi.model.PasswordProfile getPasswordProfile(){

    return passwordProfile;
  }

  public void setPasswordProfile(final org.xitikit.blue.graphapi.model.PasswordProfile passwordProfile){

    this.passwordProfile = passwordProfile;
  }

  public List<SignInName> getSignInNames(){

    return signInNames;
  }

  public void setSignInNames(final List<SignInName> signInNames){

    this.signInNames = signInNames;
  }

  public String getSurname(){

    return surname;
  }

  public void setSurname(final String surname){

    this.surname = surname;
  }

  public String getDisplayName(){

    return displayName;
  }

  public void setDisplayName(final String displayName){

    this.displayName = displayName;
  }

  public String getGivenName(){

    return givenName;
  }

  public void setGivenName(final String givenName){

    this.givenName = givenName;
  }

  public String getUserPrincipalName(){

    return userPrincipalName;
  }

  public void setUserPrincipalName(final String userPrincipalName){

    this.userPrincipalName = userPrincipalName;
  }

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