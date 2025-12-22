package com.max.config;

import com.max.interceptor.DemoInterceptor;
import com.max.interceptor.TokenInterceptor;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置類
@Configuration
public class WebConfig implements WebMvcConfigurer {


//    @Autowired
//    private DemoInterceptor demoInterceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;

    //添加攔截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //新增攔截器
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");//攔截所有請求
    }
}
