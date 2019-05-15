package com.qs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qs.bean.Flow;

/**
 * @author : qshomewy
 * @description : 
 */
@Mapper
public interface OracleDao {

    List<Flow> queryById(long id);
}
