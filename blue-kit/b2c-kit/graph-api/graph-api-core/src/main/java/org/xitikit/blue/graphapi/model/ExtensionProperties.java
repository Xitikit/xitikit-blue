package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtensionProperties{

    private List<ExtensionProperty> value;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    // CONSTRUCTORS

    public ExtensionProperties(){

    }

    public ExtensionProperties(final List<ExtensionProperty> value){

        this.value = value;
    }

    // GETTERS AND SETTERS

    public List<ExtensionProperty> getValue(){

        return value;
    }

    public void setValue(final List<ExtensionProperty> value){

        this.value = value;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties(){

        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value){

        this.additionalProperties.put(name, value);
    }
}
