package org.xitikit.blue.b2c.v2dot0.authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by J. Keith Hoopes on 5/5/2017.
 */
public interface B2CAuthenticationController{

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    String signOutRedirect();

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    String signInRedirect();

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
