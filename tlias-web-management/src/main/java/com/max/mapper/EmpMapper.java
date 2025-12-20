package com.max.mapper;


import com.max.dto.EmpListDTO;
import com.max.pojo.Emp;
import com.max.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//員工信息
@Mapper
public interface EmpMapper {


    // 普通的分頁查詢語句
    //@Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
    //"order by emp.update_time desc")

    // 條件查詢員工信息
    // MyBatis 會把對象當作一個 屬性集合 來解析。
    // XML 中可以直接用 #{屬性名} 來取值，例如：
    List<Emp> list(EmpQueryParam empQueryParam);

    // 新增員工信息
    //獲取數據庫自增的主鍵，用keyProperty 來指定要回傳到對象的哪個屬性封裝 -- 主鍵返回
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "    values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}," +
            "           #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    //根據id批量刪除員工信息
    void deleteByIds(List<Integer> ids);

    //根據id查詢員工信息
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    //根據id查詢員工基本信息
    void updateById(Emp emp);

    @Select("select * from emp")
    List<EmpListDTO> listAll();

    //統計員工職位人數
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    //統計員工各性別人數
    @MapKey("gender")
    List<Map<String, Object>> countEmpGenderData();

    //根據用戶名稱和密碼查詢員工信息
    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp selectByUserNameAndPassword(Emp emp);
    //---------------------------------------原始分頁查詢實現----------------------------------------------

    //分頁查詢
    //也需封裝部門名稱，所以需要在Emp添加deptName屬性
//    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc limit #{start }, #{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);

    //查詢總紀錄數
//    @Select("select count(*) from emp e left join  dept d on e.dept_id = d.id")
//    Long count();


}
