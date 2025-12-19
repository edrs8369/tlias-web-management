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

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分頁查詢: {}", studentQueryParam);
        PageResult<StudentDTO> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

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

    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加學生: {}", student);
        studentService.add(student);
        return Result.success();
    }
}
