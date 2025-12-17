package com.max.controller;

import com.max.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {



    //用MultipartFile接收文件，且名稱要與前端的name相同
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收到的參數: {},{},{}", name, age, file);

        //獲取原始文件名
        String originalFilename = file.getOriginalFilename();


        //獲取文件擴展名，找到.最後的所在索引位置
        //再用substring獲取.之後的副檔名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //新的文件名，避免文件名重複導致覆蓋
        String newFileName = UUID.randomUUID() + extension;

        //保存文件
        //transferTo() 將文件保存到指定位置
        file.transferTo(new File("C:/Users/user/Pictures/template/" + newFileName));

        String url = "/upload/" + newFileName;
        return Result.success(url);
    }
}
