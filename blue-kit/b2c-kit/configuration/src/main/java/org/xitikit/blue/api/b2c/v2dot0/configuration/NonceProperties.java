package org.xitikit.blue.api.b2c.v2dot0.configuration;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public class NonceProperties{

  /**
   * Indicates whether or not the application should pass a nonce
   * value to authentication requests.
   * Defaults to true.
   */
  private boolean disabled = true;

  /**
   * Optional.
   * Time in seconds that the nonce value is valid.
   * Defaults to 120 seconds (2 minutes).
   */
  private Integer timeout;

  /**
   * Name of the class to use as a Nonce Store if nonce use is enabled.
   * If caching is enabled, it will attempt to use the caching library that it finds on the classpath.
   * If caching is either not enabled or it is not able to find an appropriate
   * caching implementation, and an instance of the NonceStore is not already
   * configured, then it defaults to using the GreedyNonceStore and logs a warning.
   */
  private String storeClassName;

  public boolean isDisabled(){

    return disabled;
  }

  public void setDisabled(final boolean disabled){

    this.disabled = disabled;
  }

  public Integer getTimeout(){

    return timeout;
  }

  public void setTimeout(final Integer timeout){

    this.timeout = timeout;
  }

  public String getStoreClassName(){

    return storeClassName;
  }

  public void setStoreClassName(final String storeClassName){

    this.storeClassName = storeClassName;
  }
}
