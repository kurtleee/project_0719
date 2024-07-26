package com.uniview.project0719.service;

import com.uniview.project0719.dto.DeliverymanDTO;
import com.uniview.project0719.entity.Deliveryman;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 14:01
 * @description：
 * @modified By：
 * @version: $
 */
public interface DeliverymanService {
    /**
     * 通过手机号、姓名以及所属仓库查询配送员
     * @param paramData
     * @return
     */
    ResponseData<?> findDeliverymen(ParamData<DeliverymanDTO> paramData);

    /**
     * 查询无所属仓库的配送员
     * @param paramData
     * @return
     */
    ResponseData<?> findDeliverymenAvailable(ParamData<DeliverymanDTO> paramData);
}
