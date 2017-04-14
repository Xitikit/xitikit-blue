package org.xitikit.blue.graphapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@Component("graphApiUserProperties")
@ConfigurationProperties("blue-kit.user.attribute")
public class UserProperties {

}
