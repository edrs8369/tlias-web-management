package com.max.service.impl;

import com.max.mapper.DeptMapper;
import com.max.pojo.Dept;
import com.max.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer deptId) {
        deptMapper.deleteById(deptId);
    }

    @Override
    public void add(Dept dept) {

        //1.補全基礎屬性 - createTime updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now()) ;

        //2.調用mapper
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {

        //1.補全基礎屬性-updateTime
        dept.setUpdateTime(LocalDateTime.now());

        //2.調用Mapper接口方法更新
        deptMapper.update(dept);
    }


}
