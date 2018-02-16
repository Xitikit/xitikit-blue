package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author J. Keith Hoopes
 *     Copyright Xitikit.org 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginatedUsers{

    private List<GraphApiUser> users;

    //TODO: Maybe add an 'odata' object
    @JsonProperty("odata.nextLink")
    private String nextLink;

    @JsonProperty("odata.metadata")
    private String metaData;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    // CONSTRUCTORS

    public PaginatedUsers(){

    }

    public PaginatedUsers(final List<GraphApiUser> users, final String nextLink, final String metaData){

        this.users = users;
        this.nextLink = nextLink;
        this.metaData = metaData;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties(){

        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value){

        this.additionalProperties.put(name, value);
    }

    @JsonIgnore
    public String getSkiptoken(){

        if(nextLink != null && !"".equals(nextLink.trim())){
            int index = nextLink.indexOf("$skiptoken=");
            if(index != -1){
                return nextLink.substring(index + "$skiptoken=".length());
            }
        }
        return null;
    }

    // GETTERS AND SETTERS

    public List<GraphApiUser> getUsers(){

        return users;
    }

    public void setUsers(final List<GraphApiUser> users){

        this.users = users;
    }

    public String getMetaData(){

        return metaData;
    }

    public void setMetaData(final String metaData){

        this.metaData = metaData;
    }

    public String getNextLink(){

        return nextLink;
    }

    public void setNextLink(final String nextLink){

        this.nextLink = nextLink;
    }
}
