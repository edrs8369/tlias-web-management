package com.max.controller;

import com.max.pojo.OperateLog;
import com.max.pojo.PageResult;
import com.max.pojo.Result;
import com.max.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/log")
@Slf4j
@RestController
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/page")
    public Result page(@RequestParam Integer page,
                       @RequestParam Integer pageSize){
        log.info("分頁查詢: {},{}", page, pageSize);
        PageResult<OperateLog> pageResult = operateLogService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
