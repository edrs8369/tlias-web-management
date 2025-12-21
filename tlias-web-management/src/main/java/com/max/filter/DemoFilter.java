package com.max.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//指定攔截路徑
//@WebFilter(urlPatterns = "/*") //攔截所有請求
@Slf4j
public class DemoFilter implements Filter {

    //初始化方法, web服務器啟動時執行，並且只執行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法 ....");
    }

    //攔截到請求之後，執行，執行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("攔截到了請求....");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);//放行操作
    }

    //銷毀方法, web服務器關閉時執行，並且只執行一次
    @Override
    public void destroy() {
       log.info("destroy 銷毀方法 ....");
    }
}
