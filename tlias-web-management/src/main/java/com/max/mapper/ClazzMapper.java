package com.max.mapper;

import com.max.dto.ClazzDTO;
import com.max.pojo.Clazz;
import com.max.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ClazzMapper {


    //班級條件查詢
    List<ClazzDTO> list(ClazzQueryParam clazzQueryParam);

    //刪除班級
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    //新增班級
    @Insert("INSERT INTO clazz " +
            "(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "VALUES " +
            "(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void add(Clazz clazz);

    //查詢指定班級
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    //更新班級
    void update(Clazz clazz);

    //查詢所有班級
    @Select("select * from clazz")
    List<Clazz> listAll();
}
