package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.dto.ClazzDTO;
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
    public PageResult<ClazzDTO> page(ClazzQueryParam clazzQueryParam) {

        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        List<ClazzDTO> clazzList = clazzMapper.list(clazzQueryParam);

        for (ClazzDTO clazz : clazzList) {
            Emp master = empMapper.getById(clazz.getMasterId());
            if(master != null){
                clazz.setMasterName(master.getName());
            } else {
                clazz.setMasterName(null);
            }

            LocalDate today = LocalDate.now();

            if (today.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else if (today.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("已开班");
            }
        }

        Page<ClazzDTO> p = (Page<ClazzDTO>) clazzList;
        return new PageResult<ClazzDTO>(p.getTotal(), p.getResult());

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

    @Override
    public Clazz getById(Integer id) {

        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {

        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> listAll() {

        List<Clazz> clazzList = clazzMapper.listAll();
        return clazzList;
    }
}
