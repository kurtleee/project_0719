package com.uniview.project0719.controller;

import com.uniview.project0719.dto.UserManagementDTO;
import com.uniview.project0719.service.UserManagementService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "用户管理接口", description = "UserManagementController")
public class UserManagementController {
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/getUserList")
    public ResponseData<?> getUserList(@RequestBody ParamData<UserManagementDTO> paramData) {

        return userManagementService.getUserList(paramData);
    }
}
