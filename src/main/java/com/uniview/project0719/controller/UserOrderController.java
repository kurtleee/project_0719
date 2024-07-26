package com.uniview.project0719.controller;

import com.uniview.project0719.dto.OrderItemDTO;
import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.OrderItem;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.service.UserOrderService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/order")
@Tag(name = "UserOrderController", description = "用户订单控制器")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("/createOrder")
    public ResponseData<?> createOrder(@RequestBody OrderParamDTO orderParamDto) {
        try {
            return userOrderService.createOrder(orderParamDto);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/getOrderList")
    public ResponseData<?> getOrderList(@RequestBody ParamData<UserOrder> paramData) {
        try {
            return userOrderService.getUserOrderList(paramData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/getOrderDetail")
    public ResponseData<?> getOrderDetail(@RequestBody ParamData<OrderItemDTO> paramData) {
        return userOrderService.getUserOrderDetail(paramData);
    }
}
