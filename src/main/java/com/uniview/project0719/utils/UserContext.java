package com.uniview.project0719.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.util.Map;

public class UserContext {
    public static Integer getUserId() throws ParseException {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String jwt = request.getHeader("authorization");
        Map userInfo = JWTUtil.getJWTUserInfo(jwt);
        return ((Long) userInfo.get("userId")).intValue();
    }
}
