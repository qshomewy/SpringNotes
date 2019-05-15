package com.qs.configclient.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qs.configclient.config.Programmer;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@RestController
public class ConfigController {

    @Autowired
    private Programmer programmer;

    @RequestMapping("programmer")
    public String getProgrammer() {
        return programmer.toString();
    }
}
