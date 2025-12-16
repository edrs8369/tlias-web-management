package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.mapper.EmpExprMapper;
import com.max.mapper.EmpMapper;
import com.max.pojo.Emp;
import com.max.pojo.EmpExpr;
import com.max.pojo.EmpQueryParam;
import com.max.pojo.PageResult;
import com.max.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        //1.設置分頁參數(PageHelper)
        //startPage 設置分頁參數 → MyBatis 插件攔截下一條查詢 → 自動加 LIMIT/OFFSET → 結果封裝成 Page（繼承 List）。
        //因為多態的關係，Page的源碼繼承了 -> ArrayList ->　List
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.執行查詢
        List<Emp> emplist = empMapper.list(empQueryParam);

        //3.解析查詢結果， 並封裝數據
        //因為是多態，所以List0可以向下轉型
        Page<Emp> p = (Page<Emp>) emplist;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional //事務管理
    @Override
    public void save(Emp emp) {

        //1. 保存員工基本信息
        //前端沒有傳遞創建時間及修改時間
        //補足基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        int i = 1/0;
        //2. 保存員工工作經歷信息
        List<EmpExpr> exprList = emp.getExprList();
        if(exprList != null && !exprList.isEmpty()){
            //遍歷集合， 為empId賦值
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    // 基於PageHelper實現分頁查詢
    // 注意事項:
    //          1.定義的sql語句不能加分號;
    //          2.PageHelper僅僅能對緊跟在其後的第一個查詢語句進行分頁處理
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize
//            , String name, Integer gender, LocalDate begin, LocalDate end) {
//
//        //1.設置分頁參數(PageHelper)
//        //startPage 設置分頁參數 → MyBatis 插件攔截下一條查詢 → 自動加 LIMIT/OFFSET → 結果封裝成 Page（實現 List）。
//        //因為多態的關係，Page的源碼繼承了 -> ArrayList ->　List
//        PageHelper.startPage(page, pageSize);
//
//        //2.執行查詢
//        List<Emp> emplist = empMapper.list(name, gender, begin, end);
//
//        //3.解析查詢結果， 並封裝數據
//        //因為是多態，所以List0可以向下轉型
//        Page<Emp> p = (Page<Emp>) emplist;
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }

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
