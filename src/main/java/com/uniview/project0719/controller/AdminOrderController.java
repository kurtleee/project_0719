package com.uniview.project0719.controller;

import com.uniview.project0719.dto.UserOrderDTO;
import com.uniview.project0719.service.AdminOrderService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "订单管理接口", description = "AdminOrderController")
public class AdminOrderController {
    @Autowired
    private AdminOrderService adminOrderService;
    /**
     * 获取订单列表
     * @return
     */
    @PostMapping("/getOrderList")
    @Operation(summary = "获取订单列表")
    public ResponseData<?> getOrderList(@RequestBody ParamData<UserOrderDTO> paramData) {
        return adminOrderService.getOrderList(paramData);
    }

    @GetMapping("/getAdminOrderDetail/{orderId}")
    @Operation(summary = "获取订单详情")
    public ResponseData<?> getAdminOrderDetail(@PathVariable("orderId") String orderId){
        return adminOrderService.findOrderItemDetailByOrderId(orderId);
    }
}
