package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.mapper.EmpMapper;
import com.max.mapper.OperateLogMapper;
import com.max.pojo.Emp;
import com.max.pojo.OperateLog;
import com.max.pojo.PageResult;
import com.max.service.EmpService;
import com.max.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private EmpService empService;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        List<OperateLog> list = operateLogMapper.list();

        //取得使用者名字
        for (OperateLog operateLog : list) {
            Emp emp = empService.getInfo(operateLog.getOperateEmpId());
            operateLog.setOperateEmpName(emp.getName());
        }

        Page<OperateLog> p = (Page<OperateLog>) list;

        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
