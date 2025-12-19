package com.max.service;

import com.max.dto.ClazzDTO;
import com.max.pojo.Clazz;
import com.max.pojo.ClazzQueryParam;
import com.max.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    //分頁查詢班級
    PageResult<ClazzDTO> page(ClazzQueryParam clazzQueryParam);

    //刪除班級
    void delete(Integer id);

    //新增班級
    void add(Clazz clazz);

    //根據id查詢班級
    Clazz getById(Integer id);

    //修改班級
    void update(Clazz clazz);

    //查詢所有班級
    List<Clazz> listAll();
}
