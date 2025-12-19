package com.max.mapper;

import com.max.dto.StudentDTO;
import com.max.pojo.Student;
import com.max.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //班級條件查詢
    List<StudentDTO> list(StudentQueryParam studentQueryParam);

    //根據id刪除員工
    void deleteById(List<Integer> ids);

    //新增員工
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO student " +
            "(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "VALUES " +
            "(#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
    void insert(Student student);

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);


    void countViolation(Integer id, Integer score);

    //統計學生學歷信息
    @MapKey("degree")
    List<Map<String, Object>> countDegreeData();
}
