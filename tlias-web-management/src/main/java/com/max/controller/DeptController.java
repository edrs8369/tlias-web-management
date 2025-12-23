package com.max.controller;

import com.max.pojo.Dept;
import com.max.pojo.Result;
import com.max.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//用註解使用Logger
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //查詢所有部門信息
    @GetMapping
    public Result list() {
//        System.out.println("查詢全部的部門數據");
        log.info("查詢全部的部門數據");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //刪除指定id部門
    @DeleteMapping
    public Result delete(@RequestParam("id") Integer deptId) {
//      System.out.println("刪除部門，id=" + deptId);
        log.info("刪除部門，id= {}", deptId);
        deptService.deleteById(deptId);
        return Result.success();
    }

    //新增部門
    @PostMapping
    public Result save(@RequestBody Dept dept){
//        System.out.println("新增部門: " + dept);
        log.info("新增部門: {}", dept);
        deptService.save(dept);
        return Result.success();
    }

    //根據ID查詢部門
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
//        System.out.println("根據id查詢部門 : " + id);
        log.info("根據id查詢部門 : {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部門
    @PutMapping
    public Result update(@RequestBody Dept dept){

//        System.out.println("修改部門: " + dept);
        log.info("修改部門: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
