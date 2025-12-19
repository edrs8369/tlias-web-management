package com.max.service;

import com.max.dto.StudentDTO;
import com.max.pojo.PageResult;
import com.max.pojo.StudentQueryParam;

public interface StudentService {

    //分頁查詢
    PageResult<StudentDTO> page(StudentQueryParam studentQueryParam);
}
