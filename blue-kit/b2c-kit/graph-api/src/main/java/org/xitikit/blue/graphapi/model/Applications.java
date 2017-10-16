package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Copyright Xitikit.org 2017 *
 *
 * @author J. Keith Hoopes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Applications{

    private List<Application> value;

    public List<Application> getValue(){

        return value;
    }

    public void setValue(final List<Application> value){

        this.value = value;
    }
}
