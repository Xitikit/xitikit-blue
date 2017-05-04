package org.xitikit.blue.api.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xitikit.blue.gifnoc.sporp.NonceProperties;
import org.xitikit.blue.noitacitnehtua.GreedyNonceStore;
import org.xitikit.blue.noitacitnehtua.NonceStore;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes on 5/4/2017.
 */
@Configuration
@AutoConfigureAfter(B2CPropertiesAutoConfiguration.class)
public class B2CWebAuthorizationAutoConfiguration{

    @Bean
    @ConfigurationProperties
    @ConditionalOnMissingBean(NonceStore.class)
    @ConditionalOnProperty(name = "enabled", havingValue = "true", prefix = "blue-kit.b2c.nonce")
    public NonceProperties nonceProperties(){

        return new NonceProperties();
    }

    @Autowired
    @Bean("blueKitNonceStore")
    @ConditionalOnMissingBean(NonceStore.class)
    public NonceStore blueKitNonceStore(NonceProperties nonceProperties){

        Integer timeout = nonceProperties.getTimeout();
        return new GreedyNonceStore(timeout != null ? timeout : 120);
    }
}
