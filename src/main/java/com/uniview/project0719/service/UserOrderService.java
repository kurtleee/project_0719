package com.uniview.project0719.service;

import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.OrderItem;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

public interface UserOrderService {
    /**
     * 添加订单功能
     * @param orderParamDTO dto内参数为购物车id的集合以及地址id
     * @return ResponseData<?>
     */
    ResponseData<?> createOrder(OrderParamDTO orderParamDTO) throws InterruptedException, ParseException;

    /**
     * 获取客户订单列表
     * @param paramData
     * @return ResponseData<?>
     */
    ResponseData<?> getUserOrderList(ParamData<UserOrder> paramData) throws ParseException;

    /**
     * 通过订单编号查询订单详情，订单编号需要从前端传
     * @param paramData
     * @return
     */
    ResponseData<?> getUserOrderDetail(ParamData<OrderItem> paramData);
}
