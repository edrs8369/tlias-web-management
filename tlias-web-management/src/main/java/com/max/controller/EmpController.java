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
import java.util.List;

//員工管理器Controller
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    //分頁查詢
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){

        log.info("分頁查詢: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增員工
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {

        log.info("新增員工: {}", emp);

        empService.save(emp);

        return Result.success();
    }

    //刪除員工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("刪除員工id: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //根據id查詢員工信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據id查詢員工: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    //修改員工
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改員工: {}", emp);
        empService.update(emp);
        return Result.success();
    }

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
}
