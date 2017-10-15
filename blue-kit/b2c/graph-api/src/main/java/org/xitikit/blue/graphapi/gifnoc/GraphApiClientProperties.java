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
@ConfigurationProperties("blue-kit.graphApi")
public class GraphApiClientProperties{

  private String tenantId, clientId, clientSecret, baseUrl, apiVersion;

  private int timeout;

}
