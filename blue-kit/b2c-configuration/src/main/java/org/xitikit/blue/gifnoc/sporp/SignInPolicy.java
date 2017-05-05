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
public class SignInPolicy{

    /**
     * Required when not disabled.
     * This is the exact name of the policy web flow given after it was created.
     * This will often be the name you typed in when configuring the policy, but with
     * a prefix of "B2C_1_" added to it.
     */
    private String name;
    /**
     * Required when not disabled.
     * This is the registered redirect url inside of Azure for the named policy web flow.
     * The value will be passed along with the request when redirecting the user to Azure.
     * When the user finished authentication, it will be the url to which she is sent.
     */
    private String redirectUrl;
    /**
     * Optional. If you have configured azure to use your custom HTML and CSS, this is the
     * endpoint where the template resource can be found. Remember that JAvaScript is not allowed.
     */
    private String templateUrl;
    /**
     * Optional. Indicates that this policy is NOT going to be used if true.
     */
    private boolean disabled = false;
}
