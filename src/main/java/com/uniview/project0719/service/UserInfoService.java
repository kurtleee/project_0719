package com.uniview.project0719.service;

import com.nimbusds.jose.JOSEException;
import com.uniview.project0719.entity.UserInfo;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

public interface UserInfoService {
    /**
     * 获取当前登录的用户信息
     * @return
     */
    ResponseData<?> getCurrentUserInfo() throws ParseException;

    /**
     * 用户登录功能
     * @param userInfo
     * @return
     */
    ResponseData<?> userLogin(UserInfo userInfo) throws JOSEException;
}