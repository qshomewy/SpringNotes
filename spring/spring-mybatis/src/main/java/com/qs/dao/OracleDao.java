package com.qs.dao;

import java.util.List;

import com.qs.bean.Flow;

/**
 * 
 * @author : qshomewy
 * @description :
 */
public interface OracleDao {

    List<Flow> queryById(long id);
}
