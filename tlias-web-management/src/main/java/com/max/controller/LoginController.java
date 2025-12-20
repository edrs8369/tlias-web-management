package com.max.controller;


import com.max.pojo.Emp;
import com.max.pojo.LoginInfo;
import com.max.pojo.Result;
import com.max.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//登入Controller
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    //登入
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登入: {}", emp);
        LoginInfo info = empService.login(emp);
        return Result.success(info);
    }
}
