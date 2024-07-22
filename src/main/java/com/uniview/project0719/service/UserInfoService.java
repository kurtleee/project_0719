package com.uniview.project0719.service;

import com.uniview.project0719.entity.UserInfo;
import com.uniview.project0719.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }
}
