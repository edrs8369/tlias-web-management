package com.max.mapper;

import com.max.dto.StudentDTO;
import com.max.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    //班級條件查詢
    List<StudentDTO> list(StudentQueryParam studentQueryParam);
}
