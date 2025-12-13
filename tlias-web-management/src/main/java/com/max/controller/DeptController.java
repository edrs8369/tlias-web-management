package com.max.controller;

import com.max.pojo.Dept;
import com.max.pojo.Result;
import com.max.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查詢所有部門信息
    @GetMapping("/depts")
    public Result list() {
        System.out.println("查詢全部的部門數據");

        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //刪除指定id部門
    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer deptId) {
        System.out.println("刪除部門，id=" + deptId);
        deptService.deleteById(deptId);
        return Result.success();
    }

    //新增部門
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        System.out.println("添加部門: " + dept);
        deptService.add(dept);
        return Result.success();
    }

    //根據ID查詢部門
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("根據id查詢部門 : " + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
}
