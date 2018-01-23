package org.xitikit.blue.graphapi.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphApiClientProperties{

    private String tenantId, clientId, clientSecret, baseUrl, apiVersion;

    private int timeout;
}
