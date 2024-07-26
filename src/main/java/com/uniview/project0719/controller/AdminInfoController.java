package com.uniview.project0719.controller;

import com.uniview.project0719.dto.UserInfoDTO;
import com.uniview.project0719.service.UserInfoService;

import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "AdminInfoController", description = "用户管理控制器")
public class AdminInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取用户列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getUserList")
    public Page<UserInfoDTO> getUserList(@RequestParam(defaultValue = "0") int page,
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