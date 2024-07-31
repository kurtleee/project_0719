package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.UserInfoDTO;
import com.uniview.project0719.entity.UserInfo;
import com.uniview.project0719.repository.UserInfoRepository;
import com.uniview.project0719.service.UserInfoService;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public ResponseData<?> getCurrentUserInfo() throws ParseException {
        Integer userId = UserContext.getUserId();
        UserInfo userInfo = userInfoRepository.findUserInfoById(userId);
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo);
        return new ResponseData<>().success(userInfoDTO);
    }
}
