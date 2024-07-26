package com.uniview.project0719.service;

import com.uniview.project0719.dto.SorterDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 14:01
 * @description：
 * @modified By：
 * @version: $
 */
public interface SorterService {
    /**
     * 通过手机号、姓名以及所属仓库查询配送员
     * @param paramData
     * @return
     */
    ResponseData<?> findSorter(ParamData<SorterDTO> paramData);
    ResponseData<?> findSorterAvailable(ParamData<SorterDTO> paramData);
}
