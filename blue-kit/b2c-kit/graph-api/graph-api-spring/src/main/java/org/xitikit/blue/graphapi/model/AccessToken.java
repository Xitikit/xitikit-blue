package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Token data used for accessing the Azure B2C GraphAPI
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Map<String,Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String,Object> getAdditionalProperties(){

        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value){

        this.additionalProperties.put(name, value);
    }
}
