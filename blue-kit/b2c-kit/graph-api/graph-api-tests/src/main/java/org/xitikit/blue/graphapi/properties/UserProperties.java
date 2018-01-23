package org.xitikit.blue.graphapi.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProperties{

    /**
     * These are the custom attributes you have manually created inside of Azure B2CProperties
     * for users of the target tenant.
     */
    private List<String> customAttributes;
}
