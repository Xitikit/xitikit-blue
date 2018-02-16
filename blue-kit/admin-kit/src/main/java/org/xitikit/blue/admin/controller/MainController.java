package org.xitikit.blue.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xitikit.blue.graphapi.service.AzureGraphApiClient;

@RestController
@RequestMapping("/api")
public class MainController{

    private final AzureGraphApiClient azureGraphApiClient;

    @Autowired
    public MainController(
        final @Qualifier("azureGraphApiClient") AzureGraphApiClient azureGraphApiClient){

        this.azureGraphApiClient = azureGraphApiClient;
    }
}
