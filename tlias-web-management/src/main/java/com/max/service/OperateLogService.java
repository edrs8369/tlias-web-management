package com.max.service;


import com.max.pojo.OperateLog;
import com.max.pojo.PageResult;

public interface OperateLogService {

    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
