package org.xitikit.blue.graphapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xitikit.blue.graphapi.gifnoc.EnableGraphApiClient;

/**
 * Created by Keith Hoopes on 8/30/2016.
 * Copyright Xitikit.org 2017
 */
@SpringBootApplication
@EnableGraphApiClient
public class TestApplicationContext{

  public static void main(String[] args){

    SpringApplication.run(TestApplicationContext.class, args);
  }
}
