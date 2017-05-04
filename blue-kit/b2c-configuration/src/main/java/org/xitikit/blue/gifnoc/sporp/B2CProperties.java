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
public class B2CProperties{

    /**
     * This is the application id, and not the secret
     */
    private String appId;
    /**
     * This is the secret created when you clicked "Generate Key" during the application registration
     */
    private String appKey;
    /**
     * The domain associated with the Azure AD B2CProperties directory. Example: xitikitblue.onmicrosoft.com
     */
    private String domain;
    /**
     * This is the name of the "reset password" policy specific to the environment the application is deployed on.
     */
    private String resetPasswordPolicy;
    /**
     * The endpoint that a user is redirected to after completing an azure "reset password" policy flow.
     */
    private String resetPasswordRedirect; //Example: https://localhost:8443/ad/b2c/resetpassword
    /**
     * In milliseconds. How long the user has to complete an action inside of azure.
     */
    private String nonceTimeout;
    /**
     * The "not before" token sometimes comes back from microsoft in the future. In milliseconds, this lets us pad the "now"
     * time, in effect saying "as long as it's not TOO far in the future, we're okay with it."
     */
    private String notBeforePaddingMilliseconds;
}
