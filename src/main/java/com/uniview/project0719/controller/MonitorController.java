package com.uniview.project0719.controller;

import com.uniview.project0719.service.UserOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kurt LEE
 * @created 2024/07/26 - 下午3:53
 * 监控组件控制器
 */
@RestController("/monitor")
@Tag(name = "MonitorController", description = "监控组件控制器")
public class MonitorController {
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 获取所有订单数量
     * @return
     */
    @GetMapping("/getAllOrderCount")
    public Long getTotalOrderCount() {
        return userOrderService.getTotalOrderCount();
    }

    /**
     * 根据订单状态获取订单数量
     * 备注：1待支付、2待收货、3待评价、4售后处理中、5已退款、7已取消（超时自动取消）。参见MySQL t_user_order表status字段
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    public Long getOrderCountByStatus(@PathVariable Integer status) {
        return userOrderService.getOrderCountByStatus(status);
    }
}
