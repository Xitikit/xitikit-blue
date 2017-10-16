package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author J. Keith Hoopes
 *   Copyright Xitikit.org 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application{

    private String displayName;

    private String aapId;

    private String objectId;

    private String objectType;

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
}
