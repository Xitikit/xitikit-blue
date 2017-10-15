package org.xitikit.blue.noitacitnehtua.api.v2dot0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xitikit.blue.b2c.api.v2dot0.policy.AuthenticationPolicy;
import org.xitikit.blue.api.b2c.v2dot0.configuration.B2CProperties;
import org.xitikit.blue.api.b2c.v2dot0.configuration.PolicyConfiguration;
import org.xitikit.blue.b2c.api.v2dot0.policy.SignOutPolicy;
import org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces.NonceService;
import org.xitikit.blue.noitacitnehtua.api.v2dot0.interfaces.UrlService;
import org.xitikit.blue.nommoc.errors.exceptions.InternalServerErrorException;

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

  private final PolicyConfiguration policyConfiguration;

  private final B2CProperties b2CProperties;

  private final NonceService nonceService;

  @Autowired
  public SimpleUrlService(
    final PolicyConfiguration policyConfiguration,
    final B2CProperties b2CProperties,
    final NonceService nonceService){

    if(policyConfiguration == null){
      throw new IllegalArgumentException("Missing parameter 'policyConfiguration' in BlueUrlService");
    }
    if(b2CProperties == null){
      throw new IllegalArgumentException("Missing parameter 'b2CProperties' in BlueUrlService");
    }
    if(nonceService == null){
      throw new IllegalArgumentException("Missing parameter 'nonceService' in BlueUrlService");
    }

    this.policyConfiguration = policyConfiguration;
    this.b2CProperties = b2CProperties;
    this.nonceService = nonceService;
  }

  @Override @Nonnull
  public String getSignUpUrl(){

    return getPolicyUrl(policyConfiguration.getSignUpPolicy());
  }

  @Override @Nonnull
  public String getSignInUrl(){

    return getPolicyUrl(policyConfiguration.getSignInPolicy());
  }

  @Override @Nonnull
  public String getSignUpOrSignInUrl(){

    return getPolicyUrl(policyConfiguration.getSignUpOrSignInPolicy());
  }

  @Override @Nonnull
  public String getProfileUrl(){

    return getPolicyUrl(policyConfiguration.getEditProfilePolicy());
  }

  @Override @Nonnull
  public String getResetPasswordUrl(){

    return getPolicyUrl(policyConfiguration.getResetPasswordPolicy());
  }

  @Override @Nonnull
  public String getSignOutUrl(){

    SignOutPolicy signOutPolicy = policyConfiguration.getSignOutPolicy();

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

  @Override public String wellKnownEndpoint(final String policy){

    return domainUrlPart() + "/v2.0/.well-known/openid-configuration?p=" + policy;
  }

  @Nonnull
  private String getPolicyUrl(final AuthenticationPolicy authenticationPolicy){

    assert authenticationPolicy != null;

    return authenticationPolicy.isDisabled() ? ""
      : domainUrlPart() +
      policyUrlPart(authenticationPolicy.getName()) +
      clientIdUrlPart() +
      nonceUrlPart() +
      redirectUrlPart(authenticationPolicy.getRedirectUrl()) +
      STANDARD_CONFIGURATION_QUERY;
  }

  @Nonnull
  private String domainUrlPart(){

    return "https://login.microsoftonline.com/" + b2CProperties.getDomain();
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
  private static String policyUrlPart(final String policyName){

    assert policyName != null && !"".equals(policyName);
    return "/oauth2/v2.0/authorize?p=" + policyName;
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
