package org.xitikit.blue.b2c.kit.v2dot0.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xitikit.blue.api.b2c.v2dot0.configuration.B2CProperties;
import org.xitikit.blue.b2c.kit.v2dot0.policy.PolicyForB2C;
import org.xitikit.blue.b2c.kit.v2dot0.policy.SignOutPolicy;
import org.xitikit.blue.boot.b2c.SimplePolicyConfiguration;
import org.xitikit.blue.b2c.kit.v2dot0.authentication.interfaces.NonceService;
import org.xitikit.blue.b2c.kit.v2dot0.authentication.interfaces.UrlService;
import org.xitikit.blue.nommoc.errors.http.exceptions.InternalServerErrorException;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;

import static java.net.URLEncoder.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Service
public class SimpleUrlService implements UrlService{

    private static final String STANDARD_CONFIGURATION_QUERY = "&response_mode=form_post&scope=openid&response_type=id_token&prompt=login";

    private final SimplePolicyConfiguration simplePolicyConfiguration;

    private final B2CProperties b2CProperties;

    private final NonceService nonceService;

    @Autowired
    public SimpleUrlService(
      final SimplePolicyConfiguration simplePolicyConfiguration,
      final B2CProperties b2CProperties,
      final NonceService nonceService){

        if(simplePolicyConfiguration == null){
            throw new IllegalArgumentException("Missing parameter 'policyConfiguration' in BlueUrlService");
        }
        if(b2CProperties == null){
            throw new IllegalArgumentException("Missing parameter 'b2CProperties' in BlueUrlService");
        }
        if(nonceService == null){
            throw new IllegalArgumentException("Missing parameter 'nonceService' in BlueUrlService");
        }

        this.simplePolicyConfiguration = simplePolicyConfiguration;
        this.b2CProperties = b2CProperties;
        this.nonceService = nonceService;
    }

    @Override
    @Nonnull
    public String getSignUpUrl(){

        return getPolicyUrl(simplePolicyConfiguration.getSignUp());
    }

    @Override
    @Nonnull
    public String getSignInUrl(){

        return getPolicyUrl(simplePolicyConfiguration.getSignIn());
    }

    @Override
    @Nonnull
    public String getSignUpOrSignInUrl(){

        return getPolicyUrl(simplePolicyConfiguration.getSignUpOrSignIn());
    }

    @Override
    @Nonnull
    public String getProfileUrl(){

        return getPolicyUrl(simplePolicyConfiguration.getEditProfile());
    }

    @Override
    @Nonnull
    public String getResetPasswordUrl(){

        return getPolicyUrl(simplePolicyConfiguration.getResetPassword());
    }

    @Override
    @Nonnull
    public String getSignOutUrl(){

        SignOutPolicy signOutPolicy = simplePolicyConfiguration.getSignOut();

        try{
            return signOutPolicy.isDisabled() ? ""
              : domainUrlPart() +
              policyUrlPart(signOutPolicy.getName()) +
              "&post_logout_redirect_uri=" +
              encode(signOutPolicy.getRedirectUrl(), "UTF-8") +
              nonceUrlPart();

        }catch(UnsupportedEncodingException e){
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

        return policyForB2C.isDisabled() ? ""
          : domainUrlPart() +
          policyUrlPart(policyForB2C.getName()) +
          clientIdUrlPart() +
          nonceUrlPart() +
          redirectUrlPart(policyForB2C.getRedirectUrl()) +
          STANDARD_CONFIGURATION_QUERY;
    }

    @Nonnull
    private String domainUrlPart(){

        return "https://login.microsoftonline.com/" + b2CProperties.getDomain();
    }

    @Nonnull
    private static String policyUrlPart(final String policyName){

        assert policyName != null && !"".equals(policyName);
        return "/oauth2/v2.0/authorize?p=" + policyName;
    }

    @Nonnull
    private String clientIdUrlPart(){

        return "&client_Id=" + b2CProperties.getAppId();
    }

    @Nonnull
    private String nonceUrlPart(){

        return (nonceService.isDisabled() ? "" : "&nonce=" + nonceService.generate());
    }

    @Nonnull
    private static String redirectUrlPart(final String redirectUrl){

        assert redirectUrl != null : "Missing 'redirectUrl' in 'BlueUrlService::redirectUrlPart'.";

        try{
            return "&redirect_uri=" +
              encode(
                redirectUrl,
                "UTF-8");
        }catch(UnsupportedEncodingException e){
            throw new InternalServerErrorException("Unable to construct the redirect url.");
        }
    }
}
