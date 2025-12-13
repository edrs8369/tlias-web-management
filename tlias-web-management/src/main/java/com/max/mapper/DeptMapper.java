package com.max.mapper;

import com.max.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //查詢所有部門信息
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    //刪除指定id部門
    @Delete("delete from dept where id = #{deptId}")
    void deleteById(Integer deptId);

    //新增部門
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime} )")
    void insert(Dept dept);

    //根據ID查詢部門
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    //更新部門
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
