package org.xitikit.blue.graphapi.properties;

import java.util.List;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class UserProperties{

    /**
     * These are the custom attributes you have manually created inside of Azure B2CProperties
     * for users of the target tenant.
     */
    private List<String> customAttributes;

    public UserProperties(){

    }

    public UserProperties(final List<String> customAttributes){

        this.customAttributes = customAttributes;
    }

    public List<String> getCustomAttributes(){

        return customAttributes;
    }

    public void setCustomAttributes(final List<String> customAttributes){

        this.customAttributes = customAttributes;
    }
}
