package org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces;

import org.xitikit.blue.noitacitnehtua.api.v2dot0.Nonce;

/**
 * Useful service for generating and validating nonces.
 *
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
public interface NonceService{

  /**
   * Generates a new nonce and places it in the cache.
   *
   * @return the generated nonce
   */
  Nonce generate();

  /**
   * Validates a nonce and removes it from the cache if it exists.
   *
   * @param nonce the nonce to validate
   *
   * @return true if the nonce was valid, false if otherwise
   */
  boolean isValid(String nonce);

  /**
   * Indicates whether or not nonces have been configured.
   */
  boolean isDisabled();
}
