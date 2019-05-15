package com.qs.ymlprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qs.ymlprofile.config.Programmer;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@RestController
@RequestMapping("yml")
public class YmlController {

    @Autowired
    private Programmer programmer;

    @RequestMapping
    public Programmer programmer(){
        return programmer;
    }
}
