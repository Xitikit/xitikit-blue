package org.xitikit.blue.config;

import lombok.Data;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
public class NonceProperties {
    /**
     * Disabled by default (false).
     */
    private boolean enabled;
    /**
     * Time in seconds that the nonce value is value.
     */
    private int timeout;
    /**
     * The "not before" token sometimes comes back from microsoft
     * in the future. This lets us pad the "notBefore" token
     * validation, in effect saying "as long as it's not TOO far
     * in the future, we're okay with it."
     * Keep this value at "0" if at all possible unless you find
     * that you are failing "notBefore" nonce validation by a few
     * milliseconds.
     */
    private int notBeforePadding;
}
