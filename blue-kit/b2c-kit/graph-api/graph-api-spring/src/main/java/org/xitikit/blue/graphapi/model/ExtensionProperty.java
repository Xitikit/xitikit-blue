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
public class ExtensionProperty{

  private String name;

  private String dataType;

  private List<String> targetObjects;

  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  // STANDARD CONSTRUCTORS

  public ExtensionProperty(){

  }

  public ExtensionProperty(final String name, final String dataType, final List<String> targetObjects){

    this.name = name;
    this.dataType = dataType;
    this.targetObjects = targetObjects;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties(){

    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(final String name, final Object value){

    this.additionalProperties.put(name, value);
  }

  // GETTERS AND SETTERS

  public String getName(){

    return name;
  }

  public void setName(final String name){

    this.name = name;
  }

  public String getDataType(){

    return dataType;
  }

  public void setDataType(final String dataType){

    this.dataType = dataType;
  }

  public List<String> getTargetObjects(){

    return targetObjects;
  }

  public void setTargetObjects(final List<String> targetObjects){

    this.targetObjects = targetObjects;
  }
}
