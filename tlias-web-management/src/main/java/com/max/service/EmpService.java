package com.max.service;

import com.max.pojo.Emp;
import com.max.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
