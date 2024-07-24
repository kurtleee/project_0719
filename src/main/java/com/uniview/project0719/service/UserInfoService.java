package com.uniview.project0719.service;

import com.uniview.project0719.dto.UserInfoDTO;
import com.uniview.project0719.entity.UserInfo;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.data.domain.Page;

public interface UserInfoService {
    /**
     * 获取用户信息列表
     * @param page
     * @param pageSize
     * @return
     */
    Page<UserInfoDTO> getUserList(Integer page, Integer pageSize);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    ResponseData<?> deleteUser(Integer userId);
}