package com.uniview.project0719.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.nimbusds.jose.JOSEException;
import com.uniview.project0719.dto.UserInfoDTO;
import com.uniview.project0719.entity.UserInfo;
import com.uniview.project0719.repository.UserInfoRepository;
import com.uniview.project0719.service.UserInfoService;
import com.uniview.project0719.utils.JWTUtil;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Value("${login.salt}")
    String loginSalt;

    @Override
    public ResponseData<?> getCurrentUserInfo() throws ParseException {
        Integer userId = UserContext.getUserId();
        UserInfo userInfo = userInfoRepository.findUserInfoById(userId);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo);
        return new ResponseData<>().success(userInfoDTO);
    }

    @Override
    public ResponseData<?> userLogin(UserInfo userInfo) throws JOSEException {
        UserInfo userExt = userInfoRepository.findUserInfoByWxId(userInfo.getWxId());
        if (userExt == null) {
            UserInfo userRegister = new UserInfo();
            userRegister.setCreateTime(new Date().toInstant());
            userRegister.setWxId(userInfo.getWxId());
            if (userInfo.getPassword() == null) {
                return new ResponseData<>().fail(ResponseEnum.NO_AUTHORITY);
            }
            String md5Pw = SecureUtil.md5(userInfo.getPassword() + loginSalt);
            userRegister.setPassword(md5Pw);
            userRegister.setNickName("用户" + UUID.randomUUID().toString().substring(0, 12));
            userInfoRepository.save(userRegister);
            Integer userId = userInfoRepository.findUserInfoByWxId(userRegister.getWxId()).getId();
            Map map = new HashMap<>();
            map.put("userId", userId);
            map.put("nickName", userRegister.getNickName());
            String jwt = JWTUtil.createJWT(map);
            return new ResponseData<>().success(jwt);
        } else {
            String md5Pw = SecureUtil.md5(userInfo.getPassword() + loginSalt);
            UserInfo userInfoByWxIdAndPassword = userInfoRepository.findUserInfoByWxIdAndPassword(userInfo.getWxId(), md5Pw);
            if (userInfoByWxIdAndPassword != null) {
                Integer userId = userInfoByWxIdAndPassword.getId();
                Map map = new HashMap<>();
                map.put("userId", userId);
                map.put("nickName", userInfoByWxIdAndPassword.getNickName());
                String jwt = JWTUtil.createJWT(map);
                return new ResponseData<>().success(jwt);
            } else {
                return new ResponseData<>().fail(ResponseEnum.NO_AUTHORITY);
            }
        }
    }
}
