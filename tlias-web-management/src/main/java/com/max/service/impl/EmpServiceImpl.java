package com.max.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.max.dto.EmpListDTO;
import com.max.mapper.EmpExprMapper;
import com.max.mapper.EmpMapper;
import com.max.pojo.*;
import com.max.service.EmpLogService;
import com.max.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

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

    @Transactional(rollbackFor = Exception.class) //事務管理 - 默認出現運行時異常RuntimeException才會回滾
    @Override
    public void save(Emp emp) throws Exception {

        try {
            //1. 保存員工基本信息
            //前端沒有傳遞創建時間及修改時間
            //補足基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2. 保存員工工作經歷信息
            List<EmpExpr> exprList = emp.getExprList();
            if(exprList != null && !exprList.isEmpty()){
                //遍歷集合， 為empId賦值
                for (EmpExpr empExpr : exprList) {
                    empExpr.setEmpId(emp.getId());
                }
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //紀錄操作日誌
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增員工:" + emp);
            //錯誤發生時，因為在insertLog中設定了事務的傳播行為required_new，所以會到insertLog方法先創建事務2並提交
            //結束後，再回到現在的方法中的事物1，讓事務1回滾，但因為事物2提交，所以不會影響到insertLog方法
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {

        //1.刪除員工基本信息
        empMapper.deleteByIds(ids);

        //2.刪除員工工作經歷信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {

        //根據id查詢員工經歷
        List<EmpExpr> exprList = empExprMapper.getByEmpId(id);

        //根據id查詢員工信息
        Emp emp =empMapper.getById(id);

        if (emp == null) {
            throw new RuntimeException("員工不存在");
        }

        //將工作經歷封裝到emp裡面
        emp.setExprList(exprList);

        return emp;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根據id更新員工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根據id更新員工工作經歷信息
        //2.1先根據員工的id刪除原有的工作經歷
        //因為 Mapper 方法設計為「批量刪除」，即使只刪一筆，也必須把單一 id 包裝成 List 傳入。
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //2.2再添加這個員工新的工作經歷
        List<EmpExpr> exprList = emp.getExprList();
        if(exprList != null && !exprList.isEmpty()){
            for (EmpExpr empExpr : exprList) {
                empExpr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<EmpListDTO> listAll() {

        List<EmpListDTO> empList = empMapper.listAll();

        return empList;
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
