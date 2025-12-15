package com.max.service;

import com.max.pojo.Emp;
import com.max.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize
            , String name, Integer gender, LocalDate begin, LocalDate end);
}
