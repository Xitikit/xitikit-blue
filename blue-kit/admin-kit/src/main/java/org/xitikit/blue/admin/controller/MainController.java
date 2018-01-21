package org.xitikit.blue.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class MainController implements IMainController{

    @Autowired
    @Qualifier("azureGraphApiClient")
    private AzureGraphApiClient azureGraphApiClient;

}
