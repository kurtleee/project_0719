package com.uniview.project0719.service;

import com.uniview.project0719.dto.RepositoryDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

/**
 * @author ：zx
 * @date ：Created in 2024/7/25 17:41
 * @description：
 * @modified By：
 * @version: $
 */
public interface RepositoryService {
    /**
     * 获取仓库列表，并实现条件查询
     * 需要在前端对小区、骑手、分拣员集合进行计数再渲染到页面上
     * @param paramData
     * @return
     */
    ResponseData<?> findRepositories(ParamData<RepositoryDTO> paramData);

    /**
     * 通过仓库id查询仓库详情
     * @param id
     * @return
     */
    ResponseData<?> findRepositoryDetail(Integer id);
}
