package org.xitikit.blue.boot.b2c;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.xitikit.blue.graphapi.AzureGraphApiClient;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Configuration
@AutoConfigurationPackage
@ConditionalOnClass(AzureGraphApiClient.class)
public class GraphApiAutoConfiguration{

}
