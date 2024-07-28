package com.uniview.project0719.controller;

import com.uniview.project0719.dto.AdminDTO;
import com.uniview.project0719.service.AdminInfoService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "管理员信息接口", description = "AdminInfoController")
public class AdminInfoController {
    @Autowired
    private AdminInfoService adminInfoService;

    @PostMapping("/createAdmin")
    @Operation(summary = "创建管理员账号", description = "直接在账号列表由超级管理员创建账号，传入账号密码手机号及职位id")
    public ResponseData<?> createAdmin(@RequestBody AdminDTO adminDTO) {
        return adminInfoService.createAdmin(adminDTO);
    }

    @PostMapping("/getAdministratorList")
    @Operation(summary = "查询管理员账号列表")
    public ResponseData<?> getAdministratorList(@RequestBody ParamData<AdminDTO> paramData) {
        return adminInfoService.findAllAdmins(paramData);
    }
}