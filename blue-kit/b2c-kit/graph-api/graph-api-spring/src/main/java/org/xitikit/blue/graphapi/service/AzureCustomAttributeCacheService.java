package org.xitikit.blue.graphapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xitikit.blue.graphapi.AzureGraphApiClient;
import org.xitikit.blue.graphapi.model.Application;
import org.xitikit.blue.graphapi.model.ExtensionProperties;
import org.xitikit.blue.graphapi.model.ExtensionProperty;
import org.xitikit.blue.graphapi.properties.UserProperties;
import org.xitikit.blue.common.errors.exceptions.NotFoundException;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class AzureCustomAttributeCacheService implements IAzureCustomAttributeCacheService{

    private static final Logger log = LoggerFactory.getLogger(AzureCustomAttributeCacheService.class);

    private static ConcurrentHashMap<String, String> customAttributes = new ConcurrentHashMap<>();

    private final UserProperties userProperties;

    private final AzureGraphApiClient azureGraphApiClient;

    public AzureCustomAttributeCacheService(
        final UserProperties userProperties,
        final AzureGraphApiClient azureGraphApiClient){

        this.userProperties = userProperties;
        this.azureGraphApiClient = azureGraphApiClient;

        init();
    }

    private void init(){

        try{
            //TODO: Setup linked attribute names here.
        }catch(Exception e){
            final String msg = "" +
                "An error occurred while retrieving the name of an " +
                "extension property for the azure custom user attributes. Error: " +
                e.getMessage();
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }

    //    public String getLinkedAttributeName() {
    //
    //        if (customAttributes.containsKey(IS_LINKED) && StringUtils.isNotEmpty(customAttributes.get(IS_LINKED))) {
    //            return customAttributes.get(IS_LINKED);
    //        } else {
    //            org.xitikit.blue.graphapi.model.ExtensionProperty extensionProperty = getExtensionProperties(userProperties.getCustomAttributeTwoName());
    //            if (extensionProperty != null) {
    //                customAttributes.put(IS_LINKED, extensionProperty.getName());
    //                return customAttributes.get(IS_LINKED);
    //            }
    //        }
    //        throw new NotFoundException("No values found for azure extension custom isLinked attribute");
    //    }
    //
    //    public String getSignUpAttributeName() {
    //
    //        if (customAttributes.containsKey(SIGNUP_DATETIME) && StringUtils.isNotEmpty(customAttributes.get(SIGNUP_DATETIME))) {
    //            return customAttributes.get(SIGNUP_DATETIME);
    //        } else {
    //            org.xitikit.blue.graphapi.model.ExtensionProperty extensionProperty = getExtensionProperties(userProperties.getCustomAttributeOnePropertyName());
    //            if (extensionProperty != null) {
    //                customAttributes.put(SIGNUP_DATETIME, extensionProperty.getName());
    //                return customAttributes.get(SIGNUP_DATETIME);
    //            }
    //        }
    //        throw new NotFoundException("No values found for azure extension custom customAttributeOne attribute");
    //    }

    private ExtensionProperty getExtensionProperties(String propertyName){

        try{
            List<Application> applicationList = azureGraphApiClient
                .getApplications()
                .getValue();
            for(Application application : applicationList){
                if(application
                    .getDisplayName()
                    .equals("b2c-extensions-app")){
                    ExtensionProperties extensionProperties = azureGraphApiClient.getExtensionProperties(application.getObjectId());
                    if(extensionProperties != null && extensionProperties.getValue() != null){
                        for(org.xitikit.blue.graphapi.model.ExtensionProperty extensionProperty : extensionProperties.getValue()){
                            if(extensionProperty
                                .getName()
                                .contains(propertyName)){
                                return extensionProperty;
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            log.error("Cannot fetch value of azure extension for custom attribute " + e.getMessage(), e);
        }
        throw new NotFoundException("No values found for azure extension custom attribute");
    }

    @Override
    public List<String> getCustomAttributeNames(){

        return null;
    }
}
