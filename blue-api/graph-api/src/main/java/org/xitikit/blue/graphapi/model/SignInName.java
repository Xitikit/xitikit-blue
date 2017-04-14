package org.xitikit.blue.graphapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@SuppressWarnings("WeakerAccess")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInName {//userName or emailAddress

    private String
            type,
            value;
}
