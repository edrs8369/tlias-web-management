package com.max.controller;

import com.max.dto.StudentDTO;
import com.max.pojo.PageResult;
import com.max.pojo.Result;
import com.max.pojo.Student;
import com.max.pojo.StudentQueryParam;
import com.max.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/students")
@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //分頁條件查詢
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分頁查詢: {}", studentQueryParam);
        PageResult<StudentDTO> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    //批量刪除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){

        log.info("根據id刪除學生: {}", ids);

        String[] split = ids.split(",");

        List<Integer> list = new ArrayList<>();
        for (String s : split) {
            Integer n = Integer.valueOf(s.trim());
            list.add(n);
        }

        studentService.delete(list);
        return Result.success();
    }

    //新增學生
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加學生: {}", student);
        studentService.save(student);
        return Result.success();
    }

    //根據Id指定學生
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據id查詢學生: {}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    //修改學生
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改學生: {}", student);
        studentService.update(student);
        return Result.success();
    }

    //違規處理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,
                            @PathVariable Integer score){
        log.info("違規處理: {}, {}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
