package org.xitikit.blue.boot.b2c;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.xitikit.blue.b2c.kit.v2dot0.policy.*;
import org.xitikit.blue.graphapi.AzureGraphApiClient;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Configuration
@Conditional(AzureGraphApiClient.class)
public class GraphApiAutoConfiguration{


}
