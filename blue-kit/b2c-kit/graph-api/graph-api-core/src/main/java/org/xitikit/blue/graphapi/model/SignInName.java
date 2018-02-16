package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author J. Keith Hoopes
 *     Copyright Xitikit.org 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignInName{

    //TODO: Maybe change this to an enum?
    private String type;

    private String value;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    // CONSTRUCTORS

    public SignInName(){

    }

    public SignInName(final String type, final String value){

        this.type = type;
        this.value = value;
    }

    // GETTERS AND SETTERS

    public String getType(){

        return type;
    }

    public void setType(final String type){

        this.type = type;
    }

    public String getValue(){

        return value;
    }

    public void setValue(final String value){

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
