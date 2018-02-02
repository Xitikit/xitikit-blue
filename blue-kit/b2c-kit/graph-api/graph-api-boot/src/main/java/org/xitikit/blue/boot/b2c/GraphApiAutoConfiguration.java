package org.xitikit.blue.boot.b2c;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright ${year}
 *
 * @author J. Keith Hoopes
 */
@Configuration
@ConditionalOnClass(EnableGraphApiClient.class)
public class GraphApiAutoConfiguration{

}
