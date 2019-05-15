package com.qs.dao;

import java.util.List;

import com.qs.bean.Relation;

/**
 * 
 * @author : qshomewy
 * @description :
 */
public interface MysqlDao {

    List<Relation> queryById(String id);
}
