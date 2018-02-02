package org.xitikit.blue.graphapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Copyright Xitikit.org 2017
 *
 * Client which provides CRUD functionality for working with the Azure AD B2C Graph API.
 *
 * @author J. Keith Hoopes
 */
@SpringBootApplication
public class AzureGraphApiTestApp{

    private static final Logger log = LoggerFactory.getLogger(AzureGraphApiTestApp.class);

    public static void main(final String... args){

        log.debug("AzureGraphApiTestApp args: " + Arrays.toString(args));
        SpringApplication.run(AzureGraphApiTestApp.class, args);
    }

}

