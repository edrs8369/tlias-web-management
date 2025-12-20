package com.max.service;

import com.max.dto.EmpListDTO;
import com.max.pojo.Emp;
import com.max.pojo.EmpQueryParam;
import com.max.pojo.LoginInfo;
import com.max.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

//    PageResult<Emp> page(Integer page, Integer pageSize
//            , String name, Integer gender, LocalDate begin, LocalDate end);

    //分頁查詢
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增員工
    void save(Emp emp) throws Exception;

    //批量刪除員工
    void delete(List<Integer> ids);

    //根據id查詢員工信息
    Emp getInfo(Integer id);

    //修改員工信息
    void update(Emp emp);

    //查詢全部員工
    List<EmpListDTO> listAll();

    //員工登入
    LoginInfo login(Emp emp);
}
