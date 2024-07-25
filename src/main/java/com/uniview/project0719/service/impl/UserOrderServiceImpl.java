package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.*;
import com.uniview.project0719.repository.OrderItemRepository;
import com.uniview.project0719.repository.ShoppingCartRepository;
import com.uniview.project0719.repository.UserOrderRepository;
import com.uniview.project0719.service.UserOrderService;
import com.uniview.project0719.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<?> createOrder(OrderParamDTO orderParamDto) throws InterruptedException, ParseException {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findShoppingCartsByIdIsIn(orderParamDto.getIds());
        BigDecimal orderPrice = BigDecimal.ZERO;
        for (ShoppingCart cart : shoppingCartList) {
            orderPrice = orderPrice.add(cart.getBuyPrice().multiply(new BigDecimal(cart.getBuyNum())));
        }
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderPrice(orderPrice);
        String orderId = String.valueOf(new SnowflakeIdGenerator().nextId());
        userOrder.setOrderId(orderId);
        userOrder.setOrderDate(new Date().toInstant());
        userOrder.setUserId(UserContext.getUserId());
        Address address = new Address();
        address.setId(orderParamDto.getAddressId());
        userOrder.setAddress(address);
        userOrder.setStatus(1);
        userOrderRepository.save(userOrder);
        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCart cart : shoppingCartList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setGoodsName(cart.getGood().getTitle());
            orderItem.setPrice(cart.getBuyPrice());
            orderItem.setOrderId(orderId);
            orderItem.setBuyCount(cart.getBuyNum());
            orderItem.setSumPrice(cart.getBuyPrice().multiply(new BigDecimal(cart.getBuyNum())));
            orderItem.setGood(cart.getGood());
            orderItem.setCreateTime(userOrder.getOrderDate());
            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);
        // 暂未实现的接口：
        // redis延迟双删，同时更新商品销量
        // 批量修改订单状态
        // 订单支付超时
        return new ResponseData<>().success();
    }

    @Override
    public ResponseData<?> getUserOrderList(ParamData<UserOrder> paramData) throws ParseException {
        Specification<UserOrder> spec = Specification.where(Specifications.UserOrderHasStatus(paramData.getParam().getStatus())).and(Specifications.UserOrderHasUserId(UserContext.getUserId()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<UserOrder> orderPage = userOrderRepository.findAll(spec, pageable);
        return new ResponseData<>().success(orderPage);
    }

    @Override
    public ResponseData<?> getUserOrderDetail(ParamData<OrderItem> paramData) {
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<OrderItem> itemPage = orderItemRepository.findOrderItemByOrderId(paramData.getParam().getOrderId(),pageable);
        return new ResponseData<>().success(itemPage);
    }
}
