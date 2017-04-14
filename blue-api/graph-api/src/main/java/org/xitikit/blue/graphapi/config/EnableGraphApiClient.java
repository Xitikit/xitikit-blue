package org.xitikit.blue.graphapi.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(GraphApiDefaultConfiguration.class)
public @interface EnableGraphApiClient {

}
