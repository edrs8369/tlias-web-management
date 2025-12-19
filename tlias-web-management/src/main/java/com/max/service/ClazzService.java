package com.max.service;

import com.max.pojo.Clazz;
import com.max.pojo.ClazzQueryParam;
import com.max.pojo.PageResult;

public interface ClazzService {

    //分頁查詢班級
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    //刪除班級
    void delete(Integer id);
}
