package org.xitikit.blue.api.autoconfigure;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xitikit.blue.gifnoc.sporp.*;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017.
 */
@Configuration
public class B2CPropertiesAutoConfiguration{

    @Bean
    @ConditionalOnBean(B2CProperties.class)
    @ConfigurationProperties("azure-ad.b2c")
    public B2CProperties b2CProperties(){

        return new B2CProperties();
    }

    @Bean
    @ConditionalOnBean(SignUpPolicy.class)
    @ConditionalOnProperty(prefix = "azure-ad.b2c.policy.sign-up", value = {"name", "redirect-url"})
    @ConfigurationProperties("azure-ad.b2c.policy.sign-up")
    public SignUpPolicy signUpProperties(){

        return new SignUpPolicy();
    }

    @Bean
    @ConditionalOnBean(SignInPolicy.class)
    @ConditionalOnProperty(prefix = "azure-ad.b2c.policy.sign-in", value = {"name", "redirect-url"})
    @ConfigurationProperties("azure-ad.b2c.policy.sign-in")
    public SignInPolicy signInProperties(){

        return new SignInPolicy();
    }

    @Bean
    @ConditionalOnBean(SignUpOrSignInPolicy.class)
    @ConditionalOnProperty(prefix = "azure-ad.b2c.policy.sign-up-or-sign-in", value = {"name", "redirect-url"})
    @ConfigurationProperties("azure-ad.b2c.policy.sign-up-or-sign-in")
    public SignUpOrSignInPolicy signUpOrSignInProperties(){

        return new SignUpOrSignInPolicy();
    }

    @Bean
    @ConditionalOnBean(EditProfilePolicy.class)
    @ConditionalOnProperty(prefix = "azure-ad.b2c.policy.edit-profile", value = {"name", "redirect-url"})
    @ConfigurationProperties("azure-ad.b2c.policy.edit-profile")
    public EditProfilePolicy editProfileProperties(){

        return new EditProfilePolicy();
    }

    @Bean
    @ConditionalOnBean(ResetPasswordPolicy.class)
    @ConditionalOnProperty(prefix = "azure-ad.b2c.policy.edit-profile", value = {"name", "redirect-url"})
    @ConfigurationProperties("azure-ad.b2c.policy.reset-password")
    public ResetPasswordPolicy resetPasswordProperties(){

        return new ResetPasswordPolicy();
    }

    @Bean
    @ConditionalOnBean(SignOutPolicy.class)
    @ConditionalOnProperty(prefix = "azure-ad.b2c.policy.sign-out")
    @ConfigurationProperties("azure-ad.b2c.policy.sign-out")
    public SignOutPolicy signOutProperties(){

        return new SignOutPolicy();
    }

    @Bean
    @ConditionalOnBean(ChangeEmailPolicy.class)
    @ConditionalOnProperty(prefix = "azure-ad.b2c.policy.change-email", value = {"name", "redirect-url", "template-url"})
    @ConfigurationProperties("azure-ad.b2c.policy.sign-out")
    public ChangeEmailPolicy changeEmailProperties(){

        throw new NotImplementedException("'change-email' is not a valid policy (yet) provided by Microsoft's Azure AD B2C. You must manually change the email using the GraphApi instead.");
    }
}
