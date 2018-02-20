package org.xitikit.blue.b2c.v2dot0.policy.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xitikit.blue.b2c.v2dot0.policy.*;

@Configuration
@AutoConfigurationPackage
@ConditionalOnBean(annotation = EnablePoliciesAzureAdB2C.class)
@ConditionalOnProperty(
    prefix = "blue-kit.b2c.policy.",
    name = "domain")
@EnableConfigurationProperties(PolicyConfiguration.class)
public class AzureADB2CPolicyAutoConfiguration{

    @ConditionalOnMissingBean(PolicyConfiguration.class)
    @Bean
    @ConfigurationProperties("xitikit.bluekit.policy")
    public PolicyConfiguration blueKitPolicyProperties(){

        return new PolicyConfiguration(
            new SignUpPolicy(),
            new SignInPolicy(),
            new SignUpOrSignInPolicy(),
            new ResetPasswordPolicy(),
            new EditProfilePolicy(),
            new SignOutPolicy()
        );
    }

    @ConditionalOnProperty(
        prefix = "blue-kit.b2c.policy.sign-in.",
        name = "name")
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public class SignInPolicyConfiguration implements WebMvcConfigurer{

        @Autowired
        private PolicyConfiguration policyConfiguration;

        @Value("${server.context-path}")
        private String contextPath;

        @Override
        public void addViewControllers(final ViewControllerRegistry registry){

            registry.addRedirectViewController(
                (contextPath == null ? "" : contextPath.trim()) + "/bluekit/signin",
                policyConfiguration
                    .getSignIn()
                    .getRedirectUrl());
        }
    }
}
