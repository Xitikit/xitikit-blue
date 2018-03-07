package org.xitikit.blue.b2c.v2dot0.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xitikit.blue.common.properties.B2CProperties;
import org.xitikit.blue.common.properties.NonceProperties;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017.
 */
@SuppressWarnings({"SpringJavaAutowiringInspection", "SpringJavaInjectionPointsAutowiringInspection"})
@Configuration
@AutoConfigurationPackage
@ConditionalOnBean(annotation = EnableBlueKitB2C.class)
public class BlueKitB2CAutoConfiguration{

    @Bean
    @ConfigurationProperties("blue-kit.b2c.nonce")
    @ConditionalOnMissingBean(NonceProperties.class)
    public NonceProperties nonceProperties(){

        return new NonceProperties();
    }

    @ConditionalOnMissingBean(B2CProperties.class)
    @ConditionalOnBean(NonceProperties.class)
    @ConfigurationProperties("blue-kit.b2c")
    @Bean
    public B2CProperties b2cProperties(final NonceProperties nonceProperties){

        B2CProperties b2CProperties = new B2CProperties();
        b2CProperties.setNonce(nonceProperties);
        return b2CProperties;
    }

    @Bean("b2cNonceStore")
    @Autowired
    @ConditionalOnMissingBean(NonceStore.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.nonce", name = "disabled", havingValue = "true")
    public NonceStore noNonceStore(){

        return new NoNonceStore();
    }

    @Bean("b2cNonceStore")
    @Autowired
    @ConditionalOnMissingBean(
        annotation = EnableCaching.class,
        value = NonceStore.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.nonce", name = "disabled", havingValue = "false")
    public NonceStore greedyNonceStore(final NonceProperties nonceProperties){

        return new GreedyNonceStore(nonceProperties.getTimeout());
    }

    @Bean
    @Autowired
    @ConditionalOnBean(NonceProperties.class)
    @ConditionalOnMissingBean(NonceService.class)
    public NonceService nonceService(final NonceStore nonceStore, final NonceProperties nonceProperties){

        NonceService nonceService;
        if(nonceProperties.isDisabled()){
            nonceService = new NoNonceService();
        }
        else{
            //TODO: Add logic for checking whether a shared caching framework is enabled
            nonceService = new SimpleNonceService(nonceStore, nonceProperties);
        }
        return nonceService;
    }

    // Should place this in authentication boot module
    // @Bean("blueKitB2CAuthenticationService")
    // @Autowired
    // @ConditionalOnMissingBean(B2CAuthenticationService.class)
    // @ConditionalOnBean(
    //     value = {B2CProperties.class, SignUpPolicy.class, SignInPolicy.class, SignUpOrSignInPolicy.class, ResetPasswordPolicy.class, EditProfilePolicy.class, SignOutPolicy.class, NonceProperties.class, NonceService.class})
    // public B2CAuthenticationService blueKitB2CAuthenticationService(
    //     final ClaimValidationService claimValidationService,
    //     final NonceService nonceService,
    //     final B2cPolicyUrlService b2cPolicyUrlService,
    //     final RestTemplate restTemplate){
    //
    //     return new SimpleB2CAuthenticationService(
    //         claimValidationService,
    //         nonceService,
    //         b2cPolicyUrlService,
    //         restTemplate);
    // }
}
