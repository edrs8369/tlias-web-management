package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.mapper.ClazzMapper;
import com.max.mapper.EmpMapper;
import com.max.pojo.Clazz;
import com.max.pojo.ClazzQueryParam;
import com.max.pojo.Emp;
import com.max.pojo.PageResult;
import com.max.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {

        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        for (Clazz clazz : clazzList) {
            Emp master = empMapper.getById(clazz.getMasterId());
            clazz.setMasterName(master.getName());

            LocalDate today = LocalDate.now();

            if (today.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else if (today.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("已开班");
            }
        }

        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());

    }


    @Override
    public void delete(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {

        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }
}
