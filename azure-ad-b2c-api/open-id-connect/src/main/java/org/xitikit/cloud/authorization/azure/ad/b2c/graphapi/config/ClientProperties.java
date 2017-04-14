package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@Getter
@Setter
@Component("graphApiClientProperties")
@ConfigurationProperties("azure.graphApi")
public class ClientProperties {

    private String
            tenantId,
            clientId,
            clientSecret,
            baseUrl,
            apiVersion;

    private int timeout;
}