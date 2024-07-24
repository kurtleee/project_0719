package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.Address;
import com.uniview.project0719.entity.OrderItem;
import com.uniview.project0719.entity.ShoppingCart;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.repository.OrderItemRepository;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData<?> createOrder(OrderParamDTO orderParamDto) throws InterruptedException {
        // 以下四行代码仅做测试
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setGoodsId(1);
        shoppingCart.setBuyNum(100);
        shoppingCart.setBuyPrice(new BigDecimal(20));
        List<ShoppingCart> shoppingCartList = new ArrayList<>();// 通过orderParamDto.getIds()查询购物车对象集合，需要连表查询，暂未实现
        shoppingCartList.add(shoppingCart);
        BigDecimal orderPrice = BigDecimal.ZERO;
        for (ShoppingCart cart : shoppingCartList) {
            orderPrice = orderPrice.add(cart.getBuyPrice().multiply(new BigDecimal(cart.getBuyNum())));
        }
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderPrice(orderPrice);
        String orderId = String.valueOf(new SnowflakeIdGenerator().nextId());
        userOrder.setOrderId(orderId);
        userOrder.setOrderDate(new Date().toInstant());
        userOrder.setUserId(1);// 仅做测试
        Address address = new Address();
        address.setId(orderParamDto.getAddressId());
        userOrder.setAddress(address);
        userOrder.setStatus(1);
        userOrderRepository.save(userOrder);
        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCart cart : shoppingCartList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setGoodsName("goodsName");
            orderItem.setPrice(cart.getBuyPrice());
            orderItem.setOrderId(orderId);
            orderItem.setBuyCount(cart.getBuyNum());
            orderItem.setSumPrice(cart.getBuyPrice().multiply(new BigDecimal(cart.getBuyNum())));
            orderItem.setGoodsId(cart.getGoodsId());
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
}
