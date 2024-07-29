package com.uniview.project0719.service;

import com.uniview.project0719.dto.UserManagementDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

public interface UserManagementService {
    /**
     * 获取用户信息列表
     * @param paramData
     * @return
     */
    ResponseData<?> getUserList(ParamData<UserManagementDTO> paramData);
}
