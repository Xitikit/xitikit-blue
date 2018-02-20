package org.xitikit.examples.bluekit.simplewebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xitikit.blue.b2c.v2dot0.policy.PolicyConfiguration;

public class WebMvcConfig implements WebMvcConfigurer{

    @Autowired
    private PolicyConfiguration policyConfiguration;

    @Override
    public void addViewControllers(ViewControllerRegistry registry){

        //TODO: Add default "onmicrosft.com" mapping to autoconfig
        registry.addRedirectViewController("/", "");
    }
}
