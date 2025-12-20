package com.max.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//封裝登入結果類
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {

    private Integer id;
    private String username;
    private String name;
    private String token;
}
