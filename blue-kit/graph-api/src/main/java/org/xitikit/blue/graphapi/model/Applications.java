package org.xitikit.blue.graphapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Copyright Xitikit.org 2017 *
 *
 * @author J. Keith Hoopes
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Applications{

    private List<Application> value;
}
