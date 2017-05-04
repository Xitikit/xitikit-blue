package org.xitikit.blue.graphapi.gifnoc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Getter
@Setter
@Component("graphApiClientProperties")
@ConfigurationProperties("azure-ad.graphApi")
public class ClientProperties{

    private String
        tenantId,
        clientId,
        clientSecret,
        baseUrl,
        apiVersion;

    private int timeout;
}
