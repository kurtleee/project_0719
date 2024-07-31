package com.uniview.project0719.service;

import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

public interface UserInfoService {
    /**
     * 获取当前登录的用户信息
     * @return
     */
    ResponseData<?> getCurrentUserInfo() throws ParseException;
}