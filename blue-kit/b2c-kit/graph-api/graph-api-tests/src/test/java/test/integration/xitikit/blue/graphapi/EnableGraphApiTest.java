package test.integration.xitikit.blue.graphapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xitikit.blue.graphapi.AzureGraphApiClient;
import org.xitikit.blue.graphapi.properties.GraphApiClientProperties;
import org.xitikit.blue.graphapi.properties.UserProperties;
import org.xitikit.blue.graphapi.service.GraphApiUserService;

/**
 * Created by Keith Hoopes on 8/30/2016.
 * Copyright Xitikit.org 2017
 */
@Disabled("tests and examples still need implemented")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Configuration
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationContext.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EnableGraphApiTest{

    @Autowired
    @Qualifier("graphApiClientProperties")
    private GraphApiClientProperties graphApiClientProperties;

    @Autowired
    @Qualifier("graphApiUserProperties")
    private UserProperties userProperties;

    @Autowired
    private AzureGraphApiClient azureGraphApiClient;

    @Autowired
    private GraphApiUserService graphApiUserService;

    @Test
    void verifyUserService(){

        Assertions.assertNotNull(graphApiUserService);
    }

    @Test
    void verifyClient(){

        Assertions.assertNotNull(azureGraphApiClient);
    }

    @Test
    void verifyUserProperties(){

        Assertions.assertNotNull(userProperties);
    }

    @SuppressWarnings("Duplicates")
    @Test
    void verifyClientProperties(){

        Assertions.assertNotNull(graphApiClientProperties);
        Assertions.assertNotNull(graphApiClientProperties.getApiVersion());
        Assertions.assertNotNull(graphApiClientProperties.getBaseUrl());
        Assertions.assertNotNull(graphApiClientProperties.getClientId());
        Assertions.assertNotNull(graphApiClientProperties.getClientSecret());
        Assertions.assertNotNull(graphApiClientProperties.getTenantId());
        int timeout = graphApiClientProperties.getTimeout();
        Assertions.assertTrue(timeout > 0);
    }
}