package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@SuppressWarnings("WeakerAccess")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtensionProperties {

    private List<ExtensionProperty> value;
}
