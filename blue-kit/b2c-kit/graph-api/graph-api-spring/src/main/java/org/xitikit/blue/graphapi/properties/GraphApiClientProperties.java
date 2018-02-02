package org.xitikit.blue.graphapi.properties;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class GraphApiClientProperties{

    private String tenantId, clientId, clientSecret, baseUrl, apiVersion;

    private int timeout;

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
