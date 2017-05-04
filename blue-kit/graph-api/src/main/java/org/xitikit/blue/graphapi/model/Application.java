package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Application{

    private String displayName;

    private String aapId;

    private String objectId;

    private String objectType;
}
