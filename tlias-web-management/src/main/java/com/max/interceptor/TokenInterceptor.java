package com.max.interceptor;

import com.max.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.獲取到請求路徑
        String requestURI = request.getRequestURI(); //URI訪問資源的路徑(不包含前面的協議) ex:/employee/login

        //2.判斷是否為登入請求， 如果路經包含 /login, 說明是登入操作，放行
        if(requestURI.contains("/login")){
            log.info("登入請求， 放行");
            return true;
        }

        //3.獲取請求中的token
        String token = request.getHeader("token");

        //4.判斷token是否存在，如果不存在， 說明沒有用戶登入，返回錯誤信息401狀態碼
        if(token == null || token.isEmpty()){
            log.info("令牌為空， 返回401狀態碼");
            response.setStatus(401);
            return false;
        }

        //5.如果token存在， 校驗令牌， 如果校驗失敗，返回錯誤信息401狀態碼
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e){
            log.info("令牌校驗失敗， 返回401狀態碼");
            response.setStatus(401);
            return false;
        }

        //6.如果校驗通過就放行
        log.info("令牌校驗通過， 放行");
        return true;
    }
}
