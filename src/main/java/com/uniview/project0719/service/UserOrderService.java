package com.uniview.project0719.service;

import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

public interface UserOrderService {
    /**
     * 添加订单功能
     * @param orderParamDto dto内参数为购物车id的集合以及地址id
     * @return ResponseData<?>
     */
    ResponseData<?> createOrder(OrderParamDTO orderParamDto) throws InterruptedException, ParseException;

    /**
     * 获取客户订单列表
     * @param paramData
     * @return ResponseData<?>
     */
    ResponseData<?> getUserOrderList(ParamData<UserOrder> paramData) throws ParseException;
}
