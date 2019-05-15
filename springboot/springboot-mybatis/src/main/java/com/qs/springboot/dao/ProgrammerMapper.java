package com.qs.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qs.springboot.bean.Programmer;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@Mapper
public interface ProgrammerMapper {

    void save(Programmer programmer);

    Programmer selectById(int id);

    int modify(Programmer programmer);

    void delete(int id);
}
