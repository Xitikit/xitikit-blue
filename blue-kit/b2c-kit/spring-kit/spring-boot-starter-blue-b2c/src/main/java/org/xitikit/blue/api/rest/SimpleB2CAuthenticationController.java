package org.xitikit.blue.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xitikit.bluekit.b2c.v2dot0.authentication.BlueWebToken;
import org.xitikit.bluekit.b2c.v2dot0.authentication.interfaces.B2CAuthenticationService;
import org.xitikit.bluekit.b2c.v2dot0.authentication.interfaces.UrlService;

import static org.xitikit.bluekit.b2c.v2dot0.authentication.PolicyErrorCodes.*;

/**
 * Handles Azure B2C related requests and redirects.
 *
 * @author J. Keith Hoopes
 */
@Controller
@RequestMapping("/bluekit/b2c")
public class SimpleB2CAuthenticationController implements B2CAuthenticationController{

    private static final Logger log = LoggerFactory.getLogger(SimpleB2CAuthenticationController.class);

    private final B2CAuthenticationService blueKitB2CAuthenticationService;

    private final UrlService urlService;

    @Value("${security.auth.successRedirect}")
    private String authSuccessRedirect;

    @Value("${security.auth.failureRedirect}")
    private String authFailureRedirect;

    @Value("${security.auth.signupSuccessRedirect}")
    private String signupSuccessRedirect;

    @Value("${security.auth.resetPasswordSuccessRedirect}")
    private String resetPasswordSuccessRedirect;

    @Autowired
    public SimpleB2CAuthenticationController(final B2CAuthenticationService blueKitB2CAuthenticationService, final UrlService urlService){

        this.blueKitB2CAuthenticationService = blueKitB2CAuthenticationService;
        this.urlService = urlService;
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String signOutRedirect(){

        return "redirect:" + urlService.getSignOutUrl();
    }

    /**
     * Returns a redirect to the sign-in policy in Azure.
     *
     * @return the redirect
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signInRedirect(){

        return "redirect:" + urlService.getSignInUrl();
    }

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
    public String signInPost(
        @RequestParam(value = "id_token", required = false) final String idToken,
        @RequestParam(value = "error_description", required = false) final String errorDescription,
        @RequestParam(value = "error", required = false) final String error){

        return authenticatePost(idToken, errorDescription, error);
    }

    private String authenticatePost(final String idToken, final String errorDescription, final String error){

        return authenticatePost(idToken, errorDescription, error, this.authSuccessRedirect, this.authFailureRedirect);
    }

    private String authenticatePost(
        final String idToken,
        final String errorDescription,
        final String error,
        final String authSuccessRedirect,
        final String authFailureRedirect){

        final String redirect;

        if(StringUtils.isEmpty(error)){
            BlueWebToken token = blueKitB2CAuthenticationService.decodeAndVerify(idToken);

            if(token == null || token.isNewUser()){ // auth failed. User should never be new at this point
                redirect = "redirect:" + authFailureRedirect;
            }else{
                redirect = "redirect:" + authSuccessRedirect;
            }
        }else{
            if(access_denied.toString().equals(error) &&
                StringUtils.hasText(errorDescription) &&
                errorDescription.contains("forgotten")){
                redirect = "redirect:" + urlService.getResetPasswordUrl();
            }else{
                if(log.isDebugEnabled()){
                    log.debug("Received error from azure - Error: [" + error + "] Description: [" + errorDescription + "]");
                }
                redirect = "redirect:" + authFailureRedirect;
            }
        }
        return redirect;
    }

    //    /**
    //     * Returns a redirect to the sign-up policy in Azure.
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    //    public String signUpRedirect() {
    //
    //        return "redirect:" + azureB2CService.getSignUpUrl();
    //    }
    //
    //    /**
    //     * Called when sign-up is completed in Azure.
    //     *
    //     * Logic for someone that just signed up is slightly different
    //     * than other authentication posts, where the user should
    //     * already have an account. This one makes sure that the user
    //     * does not already exist.
    //     *
    //     * @param idToken the signed JWT token
    //     * @param errorDescription a specific error message that can help a developer identify the root cause of a sign-up
    //     * error
    //     * @param error an error code string that can be used to classify types of errors that occur, and can be used to
    //     * react to errors
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    //    public String signUpPost(@RequestParam(value = "id_token", required = false) String idToken,
    //                             @RequestParam(value = "error_description", required = false) String errorDescription,
    //                             @RequestParam(value = "error", required = false) String error,
    //                             HttpServletResponse res) {
    //
    //        final String redirect;
    //        if (StringUtils.isEmpty(error)) {
    //
    //            AzureToken token = azureB2CService.decodeAndVerify(idToken);
    //            boolean newUser = token != null && token.isNewUser();
    //            if (!newUser) { // auth failed. token was either null, or the user already exists
    //                redirect = "redirect:" + authFailureRedirect;
    //            }
    //            else {
    //
    //                authTokenService.setCookiesFromToken(res, token);
    //
    //                redirect = "redirect:" + signupSuccessRedirect;
    //            }
    //        }
    //        else {
    //            //No redirect for password reset here. Since this is a new account.
    //            log.debug("Received error from azure - Error: [" + error + "] Description: [" + errorDescription + "]");
    //            redirect = "redirect:" + authFailureRedirect;
    //        }
    //        return redirect;
    //    }
    //
    //    /**
    //     * Returns a redirect to the profile policy in Azure.
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    //    public String profileRedirect() {
    //
    //        return "redirect:" + azureB2CService.getProfileUrl();
    //    }
    //
    //    /**
    //     * Called when edit profile is completed on Azure
    //     *
    //     * @param idToken the signed JWT token
    //     * @param errorDescription a specific error message that can help a developer identify the root cause of a profile
    //     * error
    //     * @param error an error code string that can be used to classify types of errors that occur, and can be used to
    //     * react to errors
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    //    public String profilePost(@RequestParam(value = "id_token", required = false) String idToken,
    //                              @RequestParam(value = "error_description", required = false) String errorDescription,
    //                              @RequestParam(value = "error", required = false) String error,
    //                              HttpServletResponse res) {
    //
    //        return authenticatePost(idToken, errorDescription, error, res);
    //    }
    //
    //    /**
    //     * Returns a redirect to the reset password policy in Azure.
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    //    public String resetPasswordRedirect() {
    //
    //        String resetPasswordUrl = azureB2CService.getResetPasswordUrl();
    //        if (log.isDebugEnabled()) {
    //
    //            log.debug("resetPasswordUrl: " + resetPasswordUrl);
    //        }
    //        return "redirect:" + resetPasswordUrl;
    //    }
    //
    //    /**
    //     * Returns a redirect to the reset password policy in Azure from the edit profile page.
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/resetpasswordfromprofile", method = RequestMethod.GET)
    //    public String resetPasswordRedirectFromProfile() {
    //
    //        String resetPasswordUrl = azureB2CService.getResetPasswordFromProfileUrl();
    //        if (log.isDebugEnabled()) {
    //
    //            log.debug("resetPasswordUrl: " + resetPasswordUrl);
    //        }
    //        return "redirect:" + resetPasswordUrl;
    //    }
    //
    //    /**
    //     * Called when the reset password policy is completed on Azure.
    //     *
    //     * @param idToken the signed JTW token
    //     * @param errorDescription a specific error message that can help a developer identify the root cause of a reset
    //     * password error
    //     * @param error an error code string that can be used to classify types of errors that occur, and can be used to
    //     * react to errors
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    //    public String resetPasswordPost(
    //        @RequestParam(value = "id_token", required = false) String idToken,
    //        @RequestParam(value = "error_description", required = false) String errorDescription,
    //        @RequestParam(value = "error", required = false) String error,
    //        HttpServletResponse res) {
    //
    //        return authenticatePost(idToken, errorDescription, error, res);
    //    }
    //
    //    /**
    //     * Called when the reset password policy is completed on Azure when it started from the edit profile page.
    //     *
    //     * @param idToken the signed JTW token
    //     * @param errorDescription a specific error message that can help a developer identify the root cause of a reset
    //     * password error
    //     * @param error an error code string that can be used to classify types of errors that occur, and can be used to
    //     * react to errors
    //     *
    //     * @return the redirect
    //     */
    //    @RequestMapping(value = "/resetpasswordfromprofile", method = RequestMethod.POST)
    //    public String resetPasswordFromProfilePost(
    //        @RequestParam(value = "id_token", required = false) String idToken,
    //        @RequestParam(value = "error_description", required = false) String errorDescription,
    //        @RequestParam(value = "error", required = false) String error,
    //        HttpServletResponse res) {
    //
    //        return authenticatePost(idToken, errorDescription, error, res, resetPasswordSuccessRedirect, resetPasswordSuccessRedirect);
    //    }

}
