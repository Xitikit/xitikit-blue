package org.xitikit.blue.b2c.v2dot0.policy;

import org.xitikit.blue.common.errors.exceptions.InternalServerErrorException;
import org.xitikit.blue.common.properties.B2CProperties;
import org.xitikit.blue.common.services.nonce.NonceService;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static java.net.URLEncoder.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
public class SimpleB2cPolicyUrlService implements B2cPolicyUrlService{

    private static final String STANDARD_CONFIGURATION_QUERY = "&response_mode=form_post&scope=openid&response_type=id_token&prompt=login";

    private final B2cPolicyConfiguration b2cPolicyConfiguration;

    private final B2CProperties b2cProperties;

    private final NonceService nonceService;

    public SimpleB2cPolicyUrlService(
        final B2cPolicyConfiguration b2cPolicyConfiguration,
        final B2CProperties b2cProperties,
        final NonceService nonceService){

        if(b2cPolicyConfiguration == null){
            throw new IllegalArgumentException("Missing parameter 'b2cPolicyConfiguration' in BlueUrlService");
        }
        if(b2cProperties == null){
            throw new IllegalArgumentException("Missing parameter 'b2CProperties' in BlueUrlService");
        }
        if(nonceService == null){
            throw new IllegalArgumentException("Missing parameter 'nonceService' in BlueUrlService");
        }

        this.b2cPolicyConfiguration = b2cPolicyConfiguration;
        this.b2cProperties = b2cProperties;
        this.nonceService = nonceService;
    }

    @Override
    @Nonnull
    public String getSignUpUrl(){

        return getPolicyUrl(b2cPolicyConfiguration.getSignUp());
    }

    @Override
    @Nonnull
    public String getSignInUrl(){

        return getPolicyUrl(b2cPolicyConfiguration.getSignIn());
    }

    @Override
    @Nonnull
    public String getSignUpOrSignInUrl(){

        return getPolicyUrl(b2cPolicyConfiguration.getSignUpOrSignIn());
    }

    @Override
    @Nonnull
    public String getProfileUrl(){

        return getPolicyUrl(b2cPolicyConfiguration.getEditProfile());
    }

    @Override
    @Nonnull
    public String getResetPasswordUrl(){

        return getPolicyUrl(b2cPolicyConfiguration.getResetPassword());
    }

    @Override
    @Nonnull
    public String getSignOutUrl(){

        SignOutPolicy signOutPolicy = b2cPolicyConfiguration.getSignOut();
        if(signOutPolicy.isDisabled()){
            return "";
        }

        String redirectUrl = Optional
            .ofNullable(signOutPolicy.getRedirectUrl())
            .orElse("")
            .trim();
        if("".equals(redirectUrl)){
            throw new IllegalArgumentException("'redirect-url' is required when the SignOutPolicy is enabled.");
        }
        try{

            return domainUrlPart() +
                policyUrlPart(signOutPolicy.getName()) +
                "&post_logout_redirect_uri=" +
                encode(redirectUrl, "UTF-8") +
                nonceUrlPart();
        }
        catch(UnsupportedEncodingException e){
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public String wellKnownEndpoint(final String policy){

        return domainUrlPart() + "/v2.0/.well-known/openid-configuration?p=" + policy;
    }

    @Nonnull
    private String getPolicyUrl(final PolicyForB2C policyForB2C){

        assert policyForB2C != null;

        return policyForB2C.isDisabled() ? "" :
            domainUrlPart() +
                policyUrlPart(policyForB2C.getName()) +
                clientIdUrlPart() +
                nonceUrlPart() +
                redirectUrlPart(policyForB2C.getRedirectUrl()) +
                STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    private String domainUrlPart(){

        return "https://login.microsoftonline.com/" + b2cProperties.getDomain();
    }

    @Nonnull
    private static String policyUrlPart(final String policyName){

        assert policyName != null && !"".equals(policyName);
        return "/oauth2/v2.0/authorize?p=" + policyName;
    }

    @Nonnull
    private String clientIdUrlPart(){

        return "&client_Id=" + b2cProperties.getAppId();
    }

    @Nonnull
    private String nonceUrlPart(){

        return (nonceService.isDisabled() ? "" : "&nonce=" + nonceService.generate());
    }

    @Nonnull
    private static String redirectUrlPart(final String redirectUrl){

        assert redirectUrl != null : "Missing 'redirectUrl' in 'BlueUrlService::redirectUrlPart'.";
        try{
            return "&redirect_uri=" + encode(redirectUrl, "UTF-8");
        }
        catch(UnsupportedEncodingException e){
            throw new InternalServerErrorException("Unable to construct the redirect url.");
        }
    }

}
