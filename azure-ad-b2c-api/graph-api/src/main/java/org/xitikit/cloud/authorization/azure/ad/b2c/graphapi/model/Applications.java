package org.xitikit.blue.authorization.azure.ad.b2c.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Applications {

    private List<Application> value;
}
