package org.xitikit.blue.graphapi.gifnoc;

import lombok.Data;

import java.util.List;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@Data
public class UserProperties{

    /**
     * These are the custom attributes you have manually created inside of Azure B2CProperties
     * for users of the target tenant.
     */
    private List<String> customAttributes;
}
