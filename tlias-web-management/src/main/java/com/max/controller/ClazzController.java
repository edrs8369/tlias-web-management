package com.max.controller;

import com.max.dto.ClazzDTO;
import com.max.pojo.Clazz;
import com.max.pojo.ClazzQueryParam;
import com.max.pojo.PageResult;
import com.max.pojo.Result;
import com.max.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clazzs")
@RestController
@Slf4j
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    //條件查詢班級
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分頁查詢");
        PageResult<ClazzDTO> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    //刪除班級
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("刪除班級: {}", id);
        clazzService.delete(id);
        return Result.success();
    }

    //添加班級
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班級: {}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    //根據id查詢班級
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據id查詢班級: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
}
