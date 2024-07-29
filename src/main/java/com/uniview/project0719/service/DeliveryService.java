package com.uniview.project0719.service;

import com.uniview.project0719.dto.DeliveryDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.service
 * @Project：project_0719
 * @name：SortService
 * @Date：2024/7/26 14:04
 * @Filename：SortService
 */
public interface DeliveryService {
    /**
     * 后台系统配送监控表
     * @param paramData
     * @return
     */
    ResponseData<?> findAllDeliveries(ParamData<DeliveryDTO> paramData);

    /**
     * 查询当前配送员小程序端待分拣订单
     * @param paramData
     * @return
     */
    ResponseData<?> findDeliveriesByRepository(ParamData<DeliveryDTO> paramData) throws ParseException;

    /**
     * 通过配送表状态查询当前配送员的配送记录
     * @param paramData
     * @return
     */
    ResponseData<?> findDeliveryByStatus(ParamData<DeliveryDTO> paramData) throws ParseException;
}
