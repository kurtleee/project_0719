package com.uniview.project0719.service;

import com.uniview.project0719.dto.OrderItemDTO;
import com.uniview.project0719.dto.UserOrderDTO;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.data.domain.Page;

public interface AdminOrderService {
    /**
     * 获取订单列表（未完善）
     *
     * @param page
     * @param pageSize
     */
    Page<UserOrder> getOrderList(Integer page, Integer pageSize);
    /**
     * 获取订单列表，并实现条件查询
     * @param paramData
     * @return
     */
    ResponseData<?> getOrderList(ParamData<UserOrderDTO> paramData);
    /**
     * 通过订单id查询订单详情
     * @param id
     * @return
     */
    ResponseData<?> findOrderItemDetailByOrderId(String orderId);
}
