package org.xitikit.blue.api.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xitikit.blue.b2c.v2dot0.policy.*;
import org.xitikit.blue.common.errors.exceptions.NotFoundException;
import org.xitikit.blue.common.properties.B2CProperties;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017.
 */
@Configuration
public class B2CPolicyAutoConfiguration{

    @Bean("blueKitB2CPolicy")
    @ConditionalOnBean(B2CProperties.class)
    @ConfigurationProperties("blue-kit.b2c")
    public B2CProperties blueKitB2CProperties(){

        return new B2CProperties();
    }

    @Bean("blueKitSignUpPolicy")
    @ConditionalOnBean(SignUpPolicy.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.policy.sign-up", value = {"name", "redirect-url"})
    @ConfigurationProperties("blue-kit.b2c.policy.sign-up")
    public SignUpPolicy blueKitSignUpPolicy(){

        return new SignUpPolicy();
    }

    @Bean("blueKitSignInPolicy")
    @ConditionalOnBean(SignInPolicy.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.policy.sign-in", value = {"name", "redirect-url"})
    @ConfigurationProperties("blue-kit.b2c.policy.sign-in")
    public SignInPolicy blueKitSignInPolicy(){

        return new SignInPolicy();
    }

    @Bean("blueKitSignUpOrSignInPolicy")
    @ConditionalOnBean(SignUpOrSignInPolicy.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.policy.sign-up-or-sign-in", value = {"name", "redirect-url"})
    @ConfigurationProperties("blue-kit.b2c.policy.sign-up-or-sign-in")
    public SignUpOrSignInPolicy blueKitSignUpOrSignInPolicy(){

        return new SignUpOrSignInPolicy();
    }

    @Bean("blueKitEditProfilePolicy")
    @ConditionalOnBean(EditProfilePolicy.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.policy.edit-profile", value = {"name", "redirect-url"})
    @ConfigurationProperties("blue-kit.b2c.policy.edit-profile")
    public EditProfilePolicy blueKitEditProfilePolicy(){

        return new EditProfilePolicy();
    }

    @Bean("blueKitResetPasswordPolicy")
    @ConditionalOnBean(ResetPasswordPolicy.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.policy.edit-profile", value = {"name", "redirect-url"})
    @ConfigurationProperties("blue-kit.b2c.policy.reset-password")
    public ResetPasswordPolicy blueKitResetPasswordPolicy(){

        return new ResetPasswordPolicy();
    }

    @Bean("blueKitSignOutPolicy")
    @ConditionalOnBean(SignOutPolicy.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.policy.sign-out")
    @ConfigurationProperties("blue-kit.b2c.policy.sign-out")
    public SignOutPolicy blueKitSignOutPolicy(){

        return new SignOutPolicy();
    }

    @Bean("blueKitChangeEmailPolicy")
    @ConditionalOnBean(ChangeEmailPolicy.class)
    @ConditionalOnProperty(prefix = "blue-kit.b2c.policy.change-email", value = {"name", "redirect-url", "template-url"})
    @ConfigurationProperties("blue-kit.b2c.policy.change-email")
    public ChangeEmailPolicy blueKitChangeEmailPolicy(){

        throw new NotFoundException("'change-email' is not a valid policy (yet) provided by Microsoft's Azure AD B2C. You must manually change the email using the GraphApi instead.");
    }
}
