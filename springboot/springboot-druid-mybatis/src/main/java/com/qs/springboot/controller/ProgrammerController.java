package com.qs.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qs.springboot.bean.Programmer;
import com.qs.springboot.dao.ProgrammerDao;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@RestController
public class ProgrammerController {

    @Autowired
    private ProgrammerDao programmerDao;

    @GetMapping("/programmers")
    public List<Programmer> get() {
        return programmerDao.selectAll();
    }
}
