package org.xitikit.blue.config;

import lombok.Data;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
public class GraphApiProperties {
    /**
     * This is the id of the B2CProperties directory.
     */
    private String tenantId;
    /**
     * The AppPrincipalId that was generated inside Azure AD B2CProperties PowerShell module registration
     */
    private String clientId;
    /**
     * The Secret that was generated inside PowerShell registration
     */
    private String clientSecret;
    /**
     * The base url for the Graph API. This will be the same in all environments.
     */
    private String baseUrl;
    /**
     * The current version of the graph api. This will be the same in all environments.
     */
    private String apiVersion;
}
