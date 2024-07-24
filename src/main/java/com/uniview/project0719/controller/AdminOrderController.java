package com.uniview.project0719.controller;

import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.service.AdminOrderService;
import com.uniview.project0719.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminOrderController {
    @Autowired
    private AdminOrderService adminOrderService;
    /**
     * 获取订单列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getOrderList")
    public Page<UserOrder> getOrderList(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return adminOrderService.getOrderList(page, size);
    }
}
