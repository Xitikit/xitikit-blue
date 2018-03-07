package org.xitikit.blue.b2c.v2dot0.authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface B2CAuthenticationController{

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    String signOutRedirect();

    /**
     * Returns a redirect to the sign-in policy in Azure.
     *
     * @return the redirect
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    String signInRedirect();

    /**
     * Called when sign-in is completed on Azure.
     *
     * @param idToken the signed JWT token
     * @param errorDescription a specific error message that can help a developer identify the root cause of an
     *     authentication error
     * @param error an error code string that can be used to classify types of errors that occur, and can be used to
     *     react to errors
     *
     * @return a redirect
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    String signInPost(
        @RequestParam(value = "id_token", required = false) String idToken,
        @RequestParam(value = "error_description", required = false) String errorDescription,
        @RequestParam(value = "error", required = false) String error);

    String authenticatePost(String idToken, String errorDescription, String error);

    String authenticatePost(
        String idToken,
        String errorDescription,
        String error,
        String authSuccessRedirect,
        String authFailureRedirect);
}
