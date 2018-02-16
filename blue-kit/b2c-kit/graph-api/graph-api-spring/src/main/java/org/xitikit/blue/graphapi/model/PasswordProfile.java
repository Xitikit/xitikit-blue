package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author J. Keith Hoopes
 *     Copyright Xitikit.org 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordProfile{

    private String password;

    private Boolean forceChangePasswordNextLogin;

    public PasswordProfile(){

    }

    public PasswordProfile(final String password, final Boolean forceChangePasswordNextLogin){

        this.password = password;
        this.forceChangePasswordNextLogin = forceChangePasswordNextLogin;
    }

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

    public String getPassword(){

        return password;
    }

    public void setPassword(final String password){

        this.password = password;
    }

    public Boolean getForceChangePasswordNextLogin(){

        return forceChangePasswordNextLogin;
    }

    public void setForceChangePasswordNextLogin(final Boolean forceChangePasswordNextLogin){

        this.forceChangePasswordNextLogin = forceChangePasswordNextLogin;
    }
}
