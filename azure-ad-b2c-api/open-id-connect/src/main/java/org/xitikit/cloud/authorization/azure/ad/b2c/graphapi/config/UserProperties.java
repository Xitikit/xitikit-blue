package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@Component("graphApiUserProperties")
@ConfigurationProperties("azure.user.attribute")
public class UserProperties {

    private String
            customAttributeTwoName,
            customAttributeOnePropertyName;
}
