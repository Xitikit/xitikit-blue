package org.xitikit.blue.graphapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xitikit.blue.graphapi.properties.GraphApiClientProperties;
import org.xitikit.blue.graphapi.properties.UserProperties;
import org.xitikit.blue.graphapi.service.GraphApiUserService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Keith Hoopes on 8/30/2016.
 * Copyright Xitikit.org 2017
 */

@SuppressWarnings({"SpringJavaAutowiringInspection", "SpringJavaInjectionPointsAutowiringInspection"})
@Component
@ExtendWith(SpringExtension.class)
@SpringBootTest(
  classes = TestApplicationContext.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    assertNotNull(graphApiUserService);
  }

  @Test
  void verifyClient(){

    assertNotNull(azureGraphApiClient);
  }

  @Test
  void verifyUserProperties(){

    assertNotNull(userProperties);
  }

  @SuppressWarnings("Duplicates")
  @Test
  void verifyClientProperties(){

    assertNotNull(graphApiClientProperties);
    assertNotNull(graphApiClientProperties.getApiVersion());
    assertNotNull(graphApiClientProperties.getBaseUrl());
    assertNotNull(graphApiClientProperties.getClientId());
    assertNotNull(graphApiClientProperties.getClientSecret());
    assertNotNull(graphApiClientProperties.getTenantId());
    int timeout = graphApiClientProperties.getTimeout();
    assertTrue(timeout > 0);
  }
}