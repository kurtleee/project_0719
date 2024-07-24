package com.uniview.project0719.controller;

import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.service.UserOrderService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/createOrder")
    public ResponseData<?> createOrder(@RequestBody OrderParamDTO orderParamDto) {
        try {
            return userOrderService.createOrder(orderParamDto);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/getOrderList")
    public ResponseData<?> getOrderList(@RequestBody ParamData<UserOrder> paramData) {
        return userOrderService.getUserOrderList(paramData);
    }
}
