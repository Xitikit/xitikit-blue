package org.xitikit.blue.graphapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xitikit.blue.graphapi.config.ClientProperties;
import org.xitikit.blue.graphapi.gifnoc.UserProperties;
import org.xitikit.blue.graphapi.service.GraphApiUserService;

import static junit.framework.TestCase.*;

/**
 * Created by Keith Hoopes on 8/30/2016.
 * Copyright Xitikit.org 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationContext.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnableGraphApiTest{

  @Autowired
  @Qualifier("graphApiClientProperties")
  private ClientProperties clientProperties;

  @Autowired
  @Qualifier("graphApiUserProperties")
  private UserProperties userProperties;

  @Autowired
  private AzureGraphApiClient azureGraphApiClient;

  @Autowired
  private GraphApiUserService graphApiUserService;

  @Test
  public void verifyUserService(){

    assertNotNull(graphApiUserService);
  }

  @Test
  public void verifyClient(){

    assertNotNull(azureGraphApiClient);
  }

  @Test
  public void verifyUserProperties(){

    assertNotNull(userProperties);
  }

  @Test
  public void verifyClientProperties(){

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