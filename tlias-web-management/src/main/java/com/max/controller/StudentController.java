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
}
