package com.qs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qs.dao.MysqlDao;

/**
 * @author : qshomewy
 * @description : 
 */
@RestController
public class MysqlController {

	@Autowired
    private MysqlDao mysqlDao;
	
	@GetMapping("relation/{id}")
    public String get(@PathVariable(name = "id") String id) {
        return mysqlDao.queryById(id).get(0).toString();
    }
}
