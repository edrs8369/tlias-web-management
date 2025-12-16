package com.max.service.impl;

import com.max.mapper.EmpLogMapper;
import com.max.pojo.EmpLog;
import com.max.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW) //需要在一個新的事物中運行
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }

}