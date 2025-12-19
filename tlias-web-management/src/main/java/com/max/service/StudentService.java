package com.max.service;

import com.max.dto.StudentDTO;
import com.max.pojo.PageResult;
import com.max.pojo.Student;
import com.max.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    //分頁查詢
    PageResult<StudentDTO> page(StudentQueryParam studentQueryParam);

    //刪除學生
    void delete(List<Integer> ids);

    //新增學生
    void add(Student student);
}
