package com.uniview.project0719.service;

import com.uniview.project0719.dto.SortDTO;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.service
 * @Project：project_0719
 * @name：SortService
 * @Date：2024/7/26 14:04
 * @Filename：SortService
 */
public interface SortService {
    ResponseData<?> findAllSorts(ParamData<SortDTO> paramData);
}
