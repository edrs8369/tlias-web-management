package com.max.controller;

import com.max.pojo.ClazzCountOption;
import com.max.pojo.JobOption;
import com.max.pojo.Result;
import com.max.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/report")
@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    //統計員工職位人數
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("獲取員工職位統計人數");
        JobOption jobOption = reportService.getEmpJobDate();
        return Result.success(jobOption);
    }

    //統計員工性別人數
    @GetMapping("/empGenderData")
    public Result getEmpGenderDate(){
        log.info("獲取員工性別人數");
        List<Map<String, Object>> genderList = reportService.getEmpGenderDate();
        return Result.success(genderList);
    }

    //統計學生的學歷信息
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("獲取學生的學歷信息");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

    //統計班級學生數
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("獲取班級學生數");
        ClazzCountOption clazzCountOption = reportService.getStudentCountData();
        return Result.success(clazzCountOption);
    }
}
