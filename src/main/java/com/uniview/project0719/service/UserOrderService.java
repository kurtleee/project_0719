package com.uniview.project0719.service;

import com.uniview.project0719.entity.UserOrder;
import org.springframework.data.domain.Page;

public interface UserOrderService {
    /**
     * 获取订单列表
     *
     * @param page
     * @param pageSize
     */
    Page<UserOrder> getOrderList(Integer page, Integer pageSize);

}
