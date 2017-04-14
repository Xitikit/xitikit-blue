package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 *
 *         Token data used for accessing the Azure B2C GraphAPI
 */
@SuppressWarnings("WeakerAccess")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {

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
}
