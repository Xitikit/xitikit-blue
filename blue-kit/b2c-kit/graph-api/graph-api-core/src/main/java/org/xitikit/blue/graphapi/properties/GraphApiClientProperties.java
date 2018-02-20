package org.xitikit.blue.graphapi.properties;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class GraphApiClientProperties{

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

    private int timeout;

    // CONSTRUCTORS

    public GraphApiClientProperties(){

    }

    public GraphApiClientProperties(final String tenantId, final String clientId, final String clientSecret, final String baseUrl, final String apiVersion, final int timeout){

        this.tenantId = tenantId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
        this.timeout = timeout;
    }

    // GETTERS AND SETTERS

    public String getTenantId(){

        return tenantId;
    }

    public void setTenantId(final String tenantId){

        this.tenantId = tenantId;
    }

    public String getClientId(){

        return clientId;
    }

    public void setClientId(final String clientId){

        this.clientId = clientId;
    }

    public String getClientSecret(){

        return clientSecret;
    }

    public void setClientSecret(final String clientSecret){

        this.clientSecret = clientSecret;
    }

    public String getBaseUrl(){

        return baseUrl;
    }

    public void setBaseUrl(final String baseUrl){

        this.baseUrl = baseUrl;
    }

    public String getApiVersion(){

        return apiVersion;
    }

    public void setApiVersion(final String apiVersion){

        this.apiVersion = apiVersion;
    }

    public int getTimeout(){

        return timeout;
    }

    public void setTimeout(final int timeout){

        this.timeout = timeout;
    }
}
