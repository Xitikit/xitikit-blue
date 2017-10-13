package org.xitikit.blue.noitacitnehtua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xitikit.blue.gifnoc.sporp.AuthenticationPolicy;
import org.xitikit.blue.gifnoc.sporp.B2CProperties;
import org.xitikit.blue.gifnoc.sporp.PolicyConfiguration;
import org.xitikit.blue.gifnoc.sporp.SignOutPolicy;
import org.xitikit.blue.nommoc.errors.NotFoundException;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;

import static java.net.URLEncoder.*;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Service
public class BlueUrlService{

  private static final String STANDARD_CONFIGURATION_QUERY = "&response_mode=form_post&scope=openid&response_type=id_token&prompt=login";

  private final PolicyConfiguration policyConfiguration;

  private final B2CProperties b2CProperties;

  private final NonceService nonceService;

  @Autowired
  public BlueUrlService(
    final PolicyConfiguration policyConfiguration,
    final B2CProperties b2CProperties,
    final NonceService nonceService){

    if(policyConfiguration == null){
      throw new IllegalArgumentException("Missing parameter 'policyConfiguration' in org.xitikit.blue.noitacitnehtua.BlueUrlService");
    }
    if(b2CProperties == null){
      throw new IllegalArgumentException("Missing parameter 'b2CProperties' in org.xitikit.blue.noitacitnehtua.BlueUrlService");
    }
    if(nonceService == null){
      throw new IllegalArgumentException("Missing parameter 'nonceService' in org.xitikit.blue.noitacitnehtua.BlueUrlService");
    }

    this.policyConfiguration = policyConfiguration;
    this.b2CProperties = b2CProperties;
    this.nonceService = nonceService;
  }

  @Nonnull
  public String getSignUpUrl(){

    return getPolicyUrl(policyConfiguration.getSignUpPolicy());
  }

  @Nonnull
  public String getSignInUrl(){

    return getPolicyUrl(policyConfiguration.getSignInPolicy());
  }

  @Nonnull
  public String getSignUpOrSignInUrl(){

    return getPolicyUrl(policyConfiguration.getSignUpOrSignInPolicy());
  }

  @Nonnull
  public String getProfileUrl(){

    return getPolicyUrl(policyConfiguration.getEditProfilePolicy());
  }

  @Nonnull
  public String getResetPasswordUrl(){

    return getPolicyUrl(policyConfiguration.getResetPasswordPolicy());
  }

  @Nonnull
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

  public String wellKnownEndpoint(final String policy){

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

    assert redirectUrl != null : "Missing 'redirectUrl' in 'org.xitikit.blue.noitacitnehtua.BlueUrlService::redirectUrlPart'.";

    try{
      return "&redirect_uri=" +
        encode(
          redirectUrl,
          "UTF-8");
    }catch(UnsupportedEncodingException e){
      throw new NotFoundException(e.getMessage(), e);
    }
  }

}
