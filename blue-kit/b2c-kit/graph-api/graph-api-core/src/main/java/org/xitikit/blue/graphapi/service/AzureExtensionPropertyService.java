package org.xitikit.blue.graphapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.xitikit.blue.graphapi.model.Application;
import org.xitikit.blue.graphapi.model.ExtensionProperty;

import javax.annotation.Nonnull;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class AzureExtensionPropertyService{

    private static final Logger log = LoggerFactory.getLogger(AzureExtensionPropertyService.class);

    private final AzureGraphApiClient azureGraphApiClient;

    private Application b2cExtensionsApp;

    public AzureExtensionPropertyService(@NonNull final AzureGraphApiClient azureGraphApiClient){

        this.azureGraphApiClient = azureGraphApiClient;
        this.b2cExtensionsApp = b2cExtensionsApp();
    }

    private Application b2cExtensionsApp(){

        return azureGraphApiClient
            .getApplications()
            .getValue()
            .stream()
            .filter(app -> app != null && "b2c-extensions-app".equals(app.getDisplayName()))
            .findAny()
            .orElseThrow(() ->
                new IllegalStateException("The 'b2c-extensions-app' could not be found.")
            );
    }

    @Nonnull
    public ExtensionProperty getExtensionProperty(@NonNull final String propertyName){

        return azureGraphApiClient
            .getExtensionProperties(b2cExtensionsApp.getObjectId())
            .getValue()
            .stream()
            .filter(e ->
                e != null &&
                    e.getName() != null &&
                    e.getName().contains(propertyName))
            .findAny()
            .orElseThrow(() ->
                new IllegalArgumentException("" +
                    "An ExtensionProperty with a name matching '" +
                    propertyName + "' does not exist."));
    }
}
