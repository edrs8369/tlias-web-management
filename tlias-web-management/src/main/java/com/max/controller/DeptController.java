package com.max.controller;

import com.max.pojo.Dept;
import com.max.pojo.Result;
import com.max.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result list() {
        System.out.println("查詢全部的部門數據");

        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
}
