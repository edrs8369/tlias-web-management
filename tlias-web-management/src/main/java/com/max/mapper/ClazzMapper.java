package com.max.mapper;

import com.max.pojo.Clazz;
import com.max.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {


    //班級條件查詢
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    //刪除班級
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    //新增班級
    @Insert("INSERT INTO clazz " +
            "(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "VALUES " +
            "(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void add(Clazz clazz);
}
