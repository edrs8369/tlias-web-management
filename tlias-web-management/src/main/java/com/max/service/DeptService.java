package com.max.service;

import com.max.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer deptId);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
