package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.mapper.EmpMapper;
import com.max.pojo.Emp;
import com.max.pojo.PageResult;
import com.max.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    // 基於PageHelper實現分頁查詢
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {

        //1.設置分頁參數(PageHelper)
        //startPage 設置分頁參數 → MyBatis 插件攔截下一條查詢 → 自動加 LIMIT/OFFSET → 結果封裝成 Page（實現 List）。
        //因為多態的關係，Page的源碼繼承了 -> ArrayList ->　List
        PageHelper.startPage(page, pageSize);

        //2.執行查詢
        List<Emp> emplist = empMapper.list();

        //3.解析查詢結果， 並封裝數據
        //因為是多態，所以List0可以向下轉型
        Page<Emp> p = (Page<Emp>) emplist;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    //原始分頁查詢
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//
//        //1.調用Mapper接口， 查詢總紀錄數
//        Long count = empMapper.count();
//
//        //2.調用Mapper接口， 查詢結果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        //3.封裝結果 PageResult
//        return new PageResult<Emp>(count, rows);
//    }
}
