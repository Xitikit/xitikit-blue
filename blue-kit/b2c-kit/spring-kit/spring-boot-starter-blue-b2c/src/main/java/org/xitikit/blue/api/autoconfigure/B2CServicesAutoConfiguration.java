package org.xitikit.blue.api.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;
import org.springframework.web.client.RestTemplate;
import org.xitikit.blue.b2c.v2dot0.authentication.B2CAuthenticationService;
import org.xitikit.blue.b2c.v2dot0.authentication.ClaimValidationService;
import org.xitikit.blue.b2c.v2dot0.authentication.SimpleB2CAuthenticationService;
import org.xitikit.blue.b2c.v2dot0.policy.*;
import org.xitikit.blue.common.properties.B2CProperties;
import org.xitikit.blue.common.properties.NonceProperties;
import org.xitikit.blue.common.services.nonce.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017.
 */
@SuppressWarnings({"SpringJavaAutowiringInspection", "SpringJavaInjectionPointsAutowiringInspection"})
@Configuration
@Import(B2CPolicyAutoConfiguration.class)
@AutoConfigureAfter(B2CPolicyAutoConfiguration.class)
public class B2CServicesAutoConfiguration{

    @Bean
    @ConfigurationProperties("blue-kit.b2c.nonce")
    @ConditionalOnMissingBean(NonceProperties.class)
    public NonceProperties blueKitNonceProperties(){

        return new NonceProperties();
    }

    @Bean
    @Autowired
    @ConditionalOnMissingBean(NonceStore.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.nonce", name = "disabled", havingValue = "false")
    public NonceStore blueKitNonceStore(final NonceProperties nonceProperties){
        //TODO: Add logic for checking whether a shared caching framework is enabled
        return new GreedyNonceStore(nonceProperties.getTimeout());
    }

    @Bean
    @Autowired
    @ConditionalOnBean(NonceProperties.class)
    @ConditionalOnMissingBean(NonceService.class)
    public NonceService blueKitNonceService(final NonceStore nonceStore, final NonceProperties nonceProperties){
        //TODO: Add logic for checking whether a shared caching framework is enabled
        if(nonceProperties.isDisabled()){
            return new NonceService(){

                @Override
                @NonNull
                public Nonce generate(){

                    return new Nonce();
                }

                @Override
                public boolean isValid(final String nonce){

                    return false;
                }

                @Override
                public boolean isDisabled(){

                    return true;
                }
            };
        }
        return new SimpleNonceService(nonceStore, nonceProperties);
    }

    @Bean("blueKitB2CAuthenticationService")
    @Autowired
    @ConditionalOnMissingBean(B2CAuthenticationService.class)
    @ConditionalOnBean(
        value = {B2CProperties.class, SignUpPolicy.class, SignInPolicy.class, SignUpOrSignInPolicy.class, ResetPasswordPolicy.class, EditProfilePolicy.class, SignOutPolicy.class, NonceProperties.class, NonceService.class})
    public B2CAuthenticationService blueKitB2CAuthenticationService(
        final ClaimValidationService claimValidationService,
        final NonceService nonceService,
        final PolicyUrlService policyUrlService,
        final RestTemplate restTemplate){

        return new SimpleB2CAuthenticationService(
            claimValidationService,
            nonceService,
            policyUrlService,
            restTemplate);
    }
}
