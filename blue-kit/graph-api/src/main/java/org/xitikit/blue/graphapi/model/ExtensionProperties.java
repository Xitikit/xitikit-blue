package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@SuppressWarnings("WeakerAccess")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtensionProperties{

    private List<ExtensionProperty> value;
}
