package org.xitikit.blue.graphapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xitikit.blue.graphapi.AzureGraphApiClient;
import org.xitikit.blue.graphapi.gifnoc.UserProperties;
import org.xitikit.blue.graphapi.model.Application;
import org.xitikit.blue.graphapi.model.ExtensionProperties;
import org.xitikit.blue.graphapi.model.ExtensionProperty;
import org.xitikit.blue.nommoc.errors.NotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@SuppressWarnings({"unused", "EmptyTryBlock"})
@Slf4j
@Service
public class AzureCustomAttributeCacheService implements IAzureCustomAttributeCacheService{

    private static ConcurrentHashMap<String, String> customAttributes = new ConcurrentHashMap<>();

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private AzureGraphApiClient azureGraphApiClient;

    @PostConstruct
    public void init(){

        try{
            //TODO: Setup linked attribute names here.
        }
        catch(Exception e){
            log.error("Error occurred while retrieving name of extension property for azure custom attribute" + e.getMessage(), e);
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
            List<Application> applicationList = azureGraphApiClient.getApplications().getValue();
            for(Application application : applicationList){
                if(application.getDisplayName().equals("b2c-extensions-app")){
                    ExtensionProperties extensionProperties = azureGraphApiClient.getExtensionProperties(application.getObjectId());
                    if(extensionProperties != null && extensionProperties.getValue() != null){
                        for(org.xitikit.blue.graphapi.model.ExtensionProperty extensionProperty : extensionProperties.getValue()){
                            if(extensionProperty.getName().contains(propertyName)){
                                return extensionProperty;
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e){
            log.error("Cannot fetch value of azure extension for custom attribute " + e.getMessage(), e);
        }
        throw new NotFoundException("No values found for azure extension custom attribute");
    }

    @Override
    public List<String> getCustomAttributeNames(){

        return null;
    }
}
