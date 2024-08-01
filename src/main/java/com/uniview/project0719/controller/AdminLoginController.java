package com.uniview.project0719.controller;



import com.uniview.project0719.dto.AdminDTO;
import com.uniview.project0719.entity.AdministratorInfo;
import com.uniview.project0719.service.AdminInfoService;
import com.uniview.project0719.utils.JWTUtil;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Kurt LEE
 * @created 2024/08/01 - 下午3:08
 * 管理端管理员登陆接口
 *
 */

@RestController
@RequestMapping("/admin")
@Tag(name = "管理员登陆接口", description = "AdminLoginController")
public class AdminLoginController {
    @Autowired
    private AdminInfoService adminInfoService;

    @Value("${login.salt}")
    private String loginSalt;

    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody AdminDTO adminDTO) {
        AdministratorInfo adminInfo = adminInfoService.findByAccount(adminDTO.getAccount());
        if (adminInfo == null) {
            return new ResponseData<>().fail(ResponseEnum.USER_NOT_FOUND);
        }
        String encryptedPassword = adminInfoService.encryptPassword(adminDTO.getPassword(), loginSalt);
        if (!adminInfo.getPassword().equals(encryptedPassword)) {
            return new ResponseData<>().fail(ResponseEnum.INVALID_CREDENTIALS);
        }
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("account", adminInfo.getAccount());
        tokenData.put("role", "admin");
        try {
            String token = JWTUtil.createJWT(tokenData);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", token);
            return new ResponseData<>().success(responseData);
        } catch (Exception e) {
            return new ResponseData<>().fail(ResponseEnum.INTERVAL_ERROR);
        }
    }
}

