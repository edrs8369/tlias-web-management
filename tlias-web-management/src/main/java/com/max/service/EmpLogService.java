package com.max.service;

import com.max.pojo.EmpLog;
import org.springframework.transaction.annotation.Transactional;

public interface EmpLogService {

    public void insertLog(EmpLog empLog);

}