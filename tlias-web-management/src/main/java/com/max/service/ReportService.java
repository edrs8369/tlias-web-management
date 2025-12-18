package com.max.service;

import com.max.pojo.JobOption;

import java.util.List;
import java.util.Map;


public interface ReportService {

    //統計員工職位人數
    JobOption getEmpJobDate();

    //統計員工性別人數
    List<Map<String, Object>> getEmpGenderDate();
}
