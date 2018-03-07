package org.xitikit.blue.b2c.v2dot0.policy.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xitikit.blue.b2c.v2dot0.policy.B2cPolicyConfiguration;
import org.xitikit.blue.b2c.v2dot0.policy.B2cPolicyUrlService;
import org.xitikit.blue.b2c.v2dot0.policy.PolicyUrlUtil;
import org.xitikit.blue.b2c.v2dot0.policy.SimpleB2cPolicyUrlService;
import org.xitikit.blue.b2c.v2dot0.policy.factories.PolicyConfigurationFactory;
import org.xitikit.blue.common.properties.B2CProperties;
import org.xitikit.blue.common.services.nonce.NonceService;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.*;

@Configuration
@AutoConfigurationPackage
@ConditionalOnBean(annotation = EnableBlueKitB2CPolicies.class)
@ConditionalOnProperty(
    prefix = "blue-kit.b2c.",
    name = "domain")
public class AzureADB2CPolicyAutoConfiguration{

    @ConditionalOnMissingBean(B2cPolicyConfiguration.class)
    @ConfigurationProperties("blue-kit.b2c.policy")
    @Bean
    public B2cPolicyConfiguration b2cPolicyConfiguration(){

        return PolicyConfigurationFactory
            .instance()
            .defaultValues()
            .build();
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @ConditionalOnMissingBean(B2cPolicyUrlService.class)
    @ConditionalOnBean({B2cPolicyConfiguration.class, B2CProperties.class, NonceService.class})
    @Autowired
    @Bean
    public B2cPolicyUrlService b2cPolicyUrlService(
        final B2cPolicyConfiguration b2cPolicyConfiguration,
        final B2CProperties b2cProperties,
        final NonceService nonceService){

        return new SimpleB2cPolicyUrlService(
            b2cPolicyConfiguration,
            b2cProperties,
            nonceService);
    }

    @Configuration
    @ConditionalOnProperty(
        prefix = "blue-kit.b2c.policy.sign-in.",
        name = "name")
    @ConditionalOnBean(B2cPolicyConfiguration.class)
    @ConditionalOnWebApplication(type = SERVLET)
    public static class SignInPolicyConfiguration implements WebMvcConfigurer{

        private final B2cPolicyConfiguration b2cPolicyConfiguration;

        @Value("${server.context-path:}")
        private String contextPath;

        @Autowired
        public SignInPolicyConfiguration(final B2cPolicyConfiguration b2cPolicyConfiguration){

            assert b2cPolicyConfiguration != null;

            this.b2cPolicyConfiguration = b2cPolicyConfiguration;
        }

        @Override
        public void addViewControllers(final ViewControllerRegistry registry){

            String signInPath = PolicyUrlUtil.combineRelativePaths(contextPath, b2cPolicyConfiguration.getSignIn().getRedirectUrl());
            registry.addRedirectViewController(signInPath, signInPath);
        }
    }
}
