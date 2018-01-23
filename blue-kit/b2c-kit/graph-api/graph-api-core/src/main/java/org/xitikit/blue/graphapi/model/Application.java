package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author J. Keith Hoopes
 * Copyright Xitikit.org 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application{

    private String displayName;

    private String aapId;

    private String objectId;

    private String objectType;

    @JsonIgnore
    private Map<String,Object> additionalProperties = new HashMap<>();

    // CONSTRUCTORS

    // GETTERS AND SETTERS

    public String getDisplayName(){

        return displayName;
    }

    public void setDisplayName(final String displayName){

        this.displayName = displayName;
    }

    public String getAapId(){

        return aapId;
    }

    public void setAapId(final String aapId){

        this.aapId = aapId;
    }

    public String getObjectId(){

        return objectId;
    }

    public void setObjectId(final String objectId){

        this.objectId = objectId;
    }

    public String getObjectType(){

        return objectType;
    }

    public void setObjectType(final String objectType){

        this.objectType = objectType;
    }

    @JsonAnyGetter
    public Map<String,Object> getAdditionalProperties(){

        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value){

        this.additionalProperties.put(name, value);
    }
}
