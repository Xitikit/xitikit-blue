package org.xitikit.blue.graphapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xitikit.blue.graphapi.config.ClientProperties;
import org.xitikit.blue.graphapi.config.UserProperties;
import org.xitikit.blue.graphapi.service.GraphApiUserService;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Keith Hoopes on 8/30/2016.
 * Copyright Xitikit.org 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationContext.class)
@WebAppConfiguration
public class EnableGraphApiTest {

    @Autowired
    @Qualifier("graphApiClientProperties")
    ClientProperties clientProperties;

    @Autowired
    @Qualifier("graphApiUserProperties")
    UserProperties userProperties;

    @Autowired
    AzureGraphApiClient azureGraphApiClient;

    @Autowired
    GraphApiUserService graphApiUserService;

    @Test
    public void verifyAll() {

        verifyClientProperties();
        verifyUserProperties();
        verifyClient();
        verifyUserService();
    }

    private void verifyUserService() {

        assertNotNull(graphApiUserService);
    }

    private void verifyClient() {

        assertNotNull(azureGraphApiClient);
    }

    private void verifyUserProperties() {

        assertNotNull(userProperties);
        assertNotNull(userProperties.getCustomAttributeTwoName());
        assertNotNull(userProperties.getCustomAttributeOnePropertyName());
    }

    private void verifyClientProperties() {

        assertNotNull(clientProperties);
        assertNotNull(clientProperties.getApiVersion());
        assertNotNull(clientProperties.getBaseUrl());
        assertNotNull(clientProperties.getClientId());
        assertNotNull(clientProperties.getClientSecret());
        assertNotNull(clientProperties.getTenantId());
        int timeout = clientProperties.getTimeout();
        assertTrue(timeout > 0);
    }
}