package org.xitikit.blue.api.autoconfigure;

import lombok.NonNull;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.xitikit.blue.gifnoc.sporp.*;
import org.xitikit.blue.noitacitnehtua.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017.
 */
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
    public NonceStore blueKitNonceStore(NonceProperties nonceProperties){
        //TODO: Add logic for checking whether a shared caching framework is enabled
        return new GreedyNonceStore(nonceProperties.getTimeout());
    }

    @Bean
    @Autowired
    @ConditionalOnBean(NonceProperties.class)
    @ConditionalOnMissingBean(NonceService.class)
    public NonceService blueKitNonceService(NonceStore nonceStore, NonceProperties nonceProperties){
        //TODO: Add logic for checking whether a shared caching framework is enabled
        if(nonceProperties.isDisabled()){
            return new NonceService(){

                @Override
                @NonNull
                public Nonce generate(){

                    throw new NotImplementedException("Cannot generate nonce. The auto-configured NonceService has been disabled.");
                }

                @Override
                public boolean isValid(String nonce){

                    throw new NotImplementedException("Cannot validate nonce. The auto-configured NonceService has been disabled.");
                }
            };
        }
        return new SimpleNonceService(nonceStore, nonceProperties);
    }

    @Bean("blueKitB2CAuthenticationService")
    @Autowired
    @ConditionalOnMissingBean(B2CAuthenticationService.class)
    @ConditionalOnBean(value = {
        B2CProperties.class,
        SignUpPolicy.class,
        SignInPolicy.class,
        SignUpOrSignInPolicy.class,
        ResetPasswordPolicy.class,
        EditProfilePolicy.class,
        SignOutPolicy.class,
        NonceProperties.class,
        NonceService.class})
    public B2CAuthenticationService blueKitB2CAuthenticationService(
        B2CProperties b2CProperties,
        SignUpPolicy signUpPolicy,
        SignInPolicy signInPolicy,
        SignUpOrSignInPolicy signUpOrSignInPolicy,
        ResetPasswordPolicy resetPasswordPolicy,
        EditProfilePolicy editProfilePolicy,
        SignOutPolicy signOutPolicy,
        NonceProperties nonceProperties,
        NonceService nonceService){

        return new SimpleB2CAuthenticationService(
            b2CProperties,
            signUpPolicy,
            signInPolicy,
            signUpOrSignInPolicy,
            resetPasswordPolicy,
            editProfilePolicy,
            signOutPolicy,
            nonceProperties,
            nonceService
        );
    }
}
