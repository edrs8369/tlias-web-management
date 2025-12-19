package com.max.service;

import com.max.pojo.ClazzCountOption;
import com.max.pojo.JobOption;

import java.util.List;
import java.util.Map;


public interface ReportService {

    //統計員工職位人數
    JobOption getEmpJobDate();

    //統計員工性別人數
    List<Map<String, Object>> getEmpGenderDate();

    //統計學生的學歷信息
    List<Map<String, Object>> getStudentDegreeData();

    //統計班級人數
    ClazzCountOption getStudentCountData();
}
