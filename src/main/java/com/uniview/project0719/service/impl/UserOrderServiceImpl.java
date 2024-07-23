package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.repository.UserOrderRepository;
import com.uniview.project0719.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;

    /**
     * 获取订单列表
     *
     * @param page
     * @param pageSize
     */
    @Override
    public Page<UserOrder> getOrderList(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserOrder> userOrderPage = userOrderRepository.findAll(pageable);
        return userOrderPage.map(userOrder -> {
            UserOrder userOrder1 = new UserOrder();
            userOrder1.setOrderId(userOrder.getOrderId());
            userOrder1.setStatus(userOrder.getStatus());
            userOrder1.setOrderDate(userOrder.getOrderDate());
            return userOrder1;
        });

    }
}
