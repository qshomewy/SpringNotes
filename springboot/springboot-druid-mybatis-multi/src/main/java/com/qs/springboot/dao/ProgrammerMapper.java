package com.qs.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qs.springboot.bean.Programmer;

import java.util.List;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@Mapper
public interface ProgrammerMapper {

    List<Programmer> selectAll(String dataSource);

    void save(Programmer programmer);

    Programmer selectById(int id);

    int modify(String dataSource,@Param("pro") Programmer programmer);

    void delete(int id);
}
