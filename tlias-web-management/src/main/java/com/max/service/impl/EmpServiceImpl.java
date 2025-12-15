package com.max.service.impl;

import com.max.mapper.EmpMapper;
import com.max.pojo.Emp;
import com.max.pojo.PageResult;
import com.max.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {

        //1.調用Mapper接口， 查詢總紀錄數
        Long count = empMapper.count();

        //2.調用Mapper接口， 查詢結果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3.封裝結果 PageResult
        return new PageResult<Emp>(count, rows);
    }
}
