package com.max.controller;

import com.max.pojo.Emp;
import com.max.pojo.EmpQueryParam;
import com.max.pojo.PageResult;
import com.max.pojo.Result;
import com.max.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

//員工管理器Controller
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    //分頁查詢
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       @RequestParam(required = false) String name,
//                       @RequestParam(required = false) Integer gender,
//                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("分頁查詢: {},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    //分頁查詢
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){

        log.info("分頁查詢: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增員工
    @PostMapping
    public Result save(@RequestBody Emp emp){

        log.info("新增員工: {}", emp);

        empService.save(emp);

        return Result.success();
    }
}
