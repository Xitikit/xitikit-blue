package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.AzureGraphApiClient;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.config.UserProperties;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model.Application;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model.ExtensionProperties;
import org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model.ExtensionProperty;
import org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.errors.NotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@Slf4j
@Service
public class AzureCustomAttributeCacheServiceImpl implements AzureCustomAttributeCacheService {

    private static final String IS_LINKED = "IS_LINKED";

    private static final String SIGNUP_DATETIME = "SIGNUP_DATETIME";

    private static ConcurrentHashMap<String, String> customAttributes = new ConcurrentHashMap<>();

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private AzureGraphApiClient azureGraphApiClient;

    @PostConstruct
    public void init() {

        try {
            getLinkedAttributeName();
            getSignupAttributeName();
        } catch (Exception e) {
            log.error("Error occurred while retrieving name of extension property for azure custom attribute" + e.getMessage(), e);
        }
    }

    @Override
    public String getLinkedAttributeName() {

        if (customAttributes.containsKey(IS_LINKED) && StringUtils.isNotEmpty(customAttributes.get(IS_LINKED))) {
            return customAttributes.get(IS_LINKED);
        } else {
            ExtensionProperty extensionProperty = getExtensionProperties(userProperties.getCustomAttributeTwoName());
            if (extensionProperty != null) {
                customAttributes.put(IS_LINKED, extensionProperty.getName());
                return customAttributes.get(IS_LINKED);
            }
        }
        throw new NotFoundException("No values found for azure extension custom isLinked attribute");
    }

    @Override
    public String getSignupAttributeName() {

        if (customAttributes.containsKey(SIGNUP_DATETIME) && StringUtils.isNotEmpty(customAttributes.get(SIGNUP_DATETIME))) {
            return customAttributes.get(SIGNUP_DATETIME);
        } else {
            ExtensionProperty extensionProperty = getExtensionProperties(userProperties.getCustomAttributeOnePropertyName());
            if (extensionProperty != null) {
                customAttributes.put(SIGNUP_DATETIME, extensionProperty.getName());
                return customAttributes.get(SIGNUP_DATETIME);
            }
        }
        throw new NotFoundException("No values found for azure extension custom customAttributeOne attribute");
    }

    private ExtensionProperty getExtensionProperties(String propertyName) {

        try {
            List<Application> applicationList = azureGraphApiClient.getApplications().getValue();
            for (Application application : applicationList) {
                if (application.getDisplayName().equals("b2c-extensions-app")) {
                    ExtensionProperties extensionProperties = azureGraphApiClient.getExtensionProperties(application.getObjectId());
                    if (extensionProperties != null && extensionProperties.getValue() != null) {
                        for (ExtensionProperty extensionProperty : extensionProperties.getValue()) {
                            if (extensionProperty.getName().contains(propertyName)) {
                                return extensionProperty;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Cannot fetch value of azure extension for custom attribute " + e.getMessage(), e);
        }
        throw new NotFoundException("No values found for azure extension custom attribute");
    }
}
