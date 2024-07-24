package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.repository.UserOrderRepository;
import com.uniview.project0719.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;


        /**
         * 获取订单列表
         *
         * @param page
         * @param size
         */
        @Override
        public Page<UserOrder> getOrderList(Integer page, Integer size) {
            Pageable pageable = PageRequest.of(page, size);
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

