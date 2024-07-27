package com.uniview.project0719.service;

import com.uniview.project0719.dto.UserOrderDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

public interface AdminOrderService {
    /**
     * 获取订单列表，并实现条件查询
     * @param paramData
     * @return
     */
    ResponseData<?> getOrderList(ParamData<UserOrderDTO> paramData);
    /**
     * 通过订单id查询订单详情
     * @param orderId
     * @return
     */
    ResponseData<?> findOrderItemDetailByOrderId(String orderId);
}
