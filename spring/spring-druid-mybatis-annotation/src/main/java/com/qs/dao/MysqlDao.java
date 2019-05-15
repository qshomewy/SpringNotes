package com.qs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qs.bean.Relation;

/**
 * @author : qshomewy
 * @description : 
 */
@Mapper
public interface MysqlDao {

	List<Relation> queryById(String id);
}
