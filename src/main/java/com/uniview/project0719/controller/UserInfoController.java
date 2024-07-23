package com.uniview.project0719.controller;

import com.uniview.project0719.dto.UserInfoDto;
import com.uniview.project0719.service.UserInfoService;

import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取用户列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getUserList")
    public Page<UserInfoDto> getUserList(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int pageSize) {
        return userInfoService.getUserList(page, pageSize);
    }
    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseData<?> deleteUser(@PathVariable Integer userId) {
        return userInfoService.deleteUser(userId);
    }

}
