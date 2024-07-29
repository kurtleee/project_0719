package com.uniview.project0719.interceptor;

import com.alibaba.fastjson.JSON;
import com.uniview.project0719.annotation.PreAuthorize;
import com.uniview.project0719.mapper.AuthMapper;
import com.uniview.project0719.utils.JWTUtil;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：zx
 * @date ：Created in 2024/7/29 19:14
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("authorization");
        int adminId = ((Long) JWTUtil.getJWTUserInfo(authorization).get("userId")).intValue();
        //根据用户的id查询当前登录用户的角色
        List<String> authCode = authMapper.findAdminAuthById(adminId);
        if (handler instanceof HandlerMethod) {//确认访问的是否是controller里面的方法
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();//获取用户正在访问的方法的对象
            PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);//获取这个方法上的PreAuthorize注解
            if (preAuthorize != null) {//表示这个方法上真的有PreAuthorize这个注解
                String[] AuthArr = preAuthorize.value();//获取这个注解上面填写的
                //当前登录用户的权限，是否在这个AuthArr数组中,在表示有权限，不在就响应无权限
                boolean b = Arrays.stream(AuthArr).anyMatch(s -> authCode.contains(s));
                if (!b) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(JSON.toJSONString(new ResponseData<>().fail(ResponseEnum.NO_AUTHORITY)));
                    return false;
                }
            }
        }
        return true;
    }
}
