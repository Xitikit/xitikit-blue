package org.xitikit.blue.gifnoc.sporp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
   * The "not before" token sometimes comes back from microsoft
   * in the future. This lets us pad the "notBefore" token
   * validation, in effect saying "as long as it's not TOO far
   * in the future, we're okay with it."
   * Keep this value at "0" if at all possible unless you find
   * that you are failing "notBefore" nonce validation by a few
   * milliseconds.
   */
  private Integer notBeforePadding;

  /**
   * Name of the class to use as a Nonce Store id nonce use is enabled.
   * If caching is enabled, it will attempt to use the caching library that it finds on the classpath.
   * If either caching is not enabled, or it is not able to find an appropriate
   * caching implementation, then it defaults to using the GreedyNonceStore and logs a warning.
   */
  private String  storeClassName;
}
