package com.uniview.project0719.controller;

import com.uniview.project0719.service.UserInfoService;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author ：zx
 * @date ：Created in 2024/7/30 10:37
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/user")
@Tag(name = "UserInfoController", description = "用户信息接口")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getCurrentUserInfo")
    @Operation(summary = "获取当前登录的用户信息")
    public ResponseData<?> getCurrentUserInfo() {
        try {
            return userInfoService.getCurrentUserInfo();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
