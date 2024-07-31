package com.uniview.project0719.controller;



import cn.hutool.crypto.SecureUtil;
import com.uniview.project0719.dto.SorterDTO;
import com.uniview.project0719.entity.Sorter;
import com.uniview.project0719.repository.SorterRepository;
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
 * @created 2024/07/31 - 下午3:36
 * @description: 分拣员登陆控制器
 */
@RestController
@RequestMapping("/login")
@Tag(name = "分拣员登陆控制器", description = "SorterLoginController")
public class SorterLoginController {

    @Autowired
    private SorterRepository sorterRepository;

    @Value("${login.salt}")
    private String loginSalt;

    @PostMapping("/sorter/login")
    public ResponseData<?> login(@RequestBody SorterDTO sorterDTO) {
        Sorter sorter = sorterRepository.findSorterById(sorterDTO.getId());
        if (sorter != null) {
            String hashedPassword = SecureUtil.md5(sorterDTO.getPassword() + loginSalt);
            if (hashedPassword.equals(sorter.getPassword())) {
                try {
                    Map<String, Object> claims = new HashMap<>();
                    claims.put("id", sorter.getId());
                    claims.put("phone", sorter.getPhone());
                    claims.put("name", sorter.getName());
                    String token = JWTUtil.createJWT(claims);
                    Map<String, String> result = new HashMap<>();
                    result.put("token", token);
                    System.out.println(token);
                    return new ResponseData<>().success(result);
                } catch (Exception e) {
                    return new ResponseData<>().fail(ResponseEnum.INTERVAL_ERROR);
                }
            } else {
                return new ResponseData<>().fail(ResponseEnum.INVALID_CREDENTIALS);
            }
        } else {
            return new ResponseData<>().fail(ResponseEnum.USER_NOT_FOUND);
        }
    }
}

