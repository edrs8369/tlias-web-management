package com.max.mapper;


import com.max.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

//員工信息
@Mapper
public interface EmpMapper {



    //---------------------------------------原始分頁查詢實現----------------------------------------------

    //分頁查詢
    //也需封裝部門名稱，所以需要在Emp添加deptName屬性
//    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc limit #{start }, #{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);

    //查詢總紀錄數
//    @Select("select count(*) from emp e left join  dept d on e.dept_id = d.id")
//    Long count();


    // 普通的分頁查詢語句
    //@Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
            //"order by emp.update_time desc")
    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
}
