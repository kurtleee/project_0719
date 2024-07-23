package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.UserInfoDto;
import com.uniview.project0719.entity.UserInfo;
import com.uniview.project0719.repository.UserInfoRepository;
import com.uniview.project0719.service.UserInfoService;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 获取用户信息列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<UserInfoDto> getUserList(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserInfo> userInfoPage = userInfoRepository.findAll(pageable);
        return userInfoPage.map(userInfo -> {
            UserInfoDto dto = new UserInfoDto();
            dto.setId(userInfo.getId());
            dto.setNickName(userInfo.getNickName());
            dto.setWxId(userInfo.getWxId());
            return dto;
        });
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseData<?> deleteUser(Integer userId) {
        userInfoRepository.deleteById(userId);
        return new ResponseData<>().success();
    }
}
