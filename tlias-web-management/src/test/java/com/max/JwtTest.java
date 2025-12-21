package com.max;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    //生成jwt令牌
    @Test
    public void testGenerateJwt(){


        Map<String, Object> dataMap =  new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        // 1.調用工具類jwts中的build方法構建JWT令牌
        // 2.用signWith指定簽名算法(可去官網jwt.io找算法類型),及指定密鑰
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoZWltYVNjaG9vbE1heHRvQmVSaWNoTWFuWWVzSGVDYW4=")
                .addClaims(dataMap) //存儲自定義信息
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000)) //指定過期時間(1小時以毫秒表示)
                .compact();//生成令牌

        System.out.println(jwt);
    }

    //解析jwt令牌
    @Test
    public void testParseJwt(){

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2NjMyOTE5OX0.X25FLmFm1OXvuYASMSdKXVIFMD53qQD2IzwFeWUXMFk";

        //解析jwt令牌
        //設置密鑰
        Claims claims = Jwts.parserBuilder().setSigningKey("aXRoZWltYVNjaG9vbE1heHRvQmVSaWNoTWFuWWVzSGVDYW4=")
                .build()//生成真正的解析器
                .parseClaimsJws(token)//解析jwt令牌
                .getBody(); //獲取自定義信息

        System.out.println(claims);
    }
}
