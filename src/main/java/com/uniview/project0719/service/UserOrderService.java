package com.uniview.project0719.service;

import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.data.domain.Page;

public interface UserOrderService {
    /**
     * 获取订单列表
     *
     * @param page
     * @param pageSize
     */
    Page<UserOrder> getOrderList(Integer page, Integer pageSize);

    /**
     * 添加订单功能
     * @param orderParamDto dto内参数为购物车id的集合以及地址id
     * @return ResponseData<?>
     */
    ResponseData<?> createOrder(OrderParamDTO orderParamDto) throws InterruptedException;

    /**
     * 获取客户订单列表
     * @param paramData
     * @return ResponseData<?>
     */
    ResponseData<?> getUserOrderList(ParamData<UserOrder> paramData);
}
