package com.uniview.project0719.service;

import com.uniview.project0719.dto.RepositoryDTO;
import com.uniview.project0719.entity.Repository;
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

    /**
     * 添加、修改仓库
     * @param repository
     * @return
     */
    ResponseData<?> updateRepository(Repository repository);
}
