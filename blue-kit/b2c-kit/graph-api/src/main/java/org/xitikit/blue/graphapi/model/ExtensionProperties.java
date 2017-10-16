package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@SuppressWarnings("WeakerAccess")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtensionProperties{

    private List<ExtensionProperty> value;

    public List<ExtensionProperty> getValue(){

        return value;
    }

    public void setValue(final List<ExtensionProperty> value){

        this.value = value;
    }
}
