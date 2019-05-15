package com.qs.springboot.dao;

import org.apache.ibatis.annotations.*;

import com.qs.springboot.bean.Programmer;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@Mapper
public interface ProgrammerDao {

    @Insert("insert into programmer (name, age, salary, birthday) VALUES (#{name}, #{age}, #{salary}, #{birthday})")
    void save(Programmer programmer);

    @Select("select * from programmer where name = #{id}")
    Programmer selectById(int id);

    @Update("update programmer set name=#{name},age=#{age},salary=#{salary},birthday=#{birthday} where id=#{id}")
    int modify(Programmer programmer);

    @Delete(" delete from programmer where id = #{id}")
    void delete(int id);
}
