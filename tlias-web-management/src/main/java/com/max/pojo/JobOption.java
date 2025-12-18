package com.max.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOption {

    //List不放泛型代表可以放任何類型， 但是錯過編譯型檢查
    private List jobList; //職位列表
    private List dataList; //數據列表
}
