package com.max.mapper;

import com.max.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 員工工作經歷
@Mapper
public interface EmpExprMapper {

    //批量保存員工的工作經歷
    //因為批量插入有n個，所以配置在xml中並使用foreach標籤，動態新增
    void insertBatch(List<EmpExpr> exprList);

    //根據ID批量刪除員工的工作經歷
    void deleteByEmpIds(List<Integer> empIds);
}
