package com.max.service.impl;

import com.max.mapper.EmpMapper;
import com.max.mapper.StudentMapper;
import com.max.pojo.JobOption;
import com.max.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobDate() {

        //1.調用mapper接口, 獲取統計數據
        List<Map<String, Object>> list = empMapper.countEmpJobData(); //map: pos=教研主管, num=1;

        //2.組裝結果並返回
        List<Object> jobList  = list.stream()
                .map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream()
                .map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderDate() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countDegreeData();
    }
}
