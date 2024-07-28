package com.uniview.project0719.service;

import com.uniview.project0719.dto.AdminDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

/**
 * @author ：zx
 * @date ：Created in 2024/7/28 11:54
 * @description：
 * @modified By：
 * @version: $
 */
public interface AdminInfoService {
    /**
     * 用于添加管理员账号
     * 需要传入账号密码手机号和职位id，其他字段设置为null
     * @param adminDTO
     * @return
     */
    ResponseData<?> createAdmin(AdminDTO adminDTO);

    /**
     * 用于查询管理员账号
     * @param paramData
     * @return
     */
    ResponseData<?> findAllAdmins(ParamData<AdminDTO> paramData);
}
