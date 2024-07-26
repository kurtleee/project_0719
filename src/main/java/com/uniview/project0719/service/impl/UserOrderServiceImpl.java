package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.OrderItemDTO;
import com.uniview.project0719.dto.OrderItemResponseDTO;
import com.uniview.project0719.dto.OrderParamDTO;
import com.uniview.project0719.entity.*;
import com.uniview.project0719.repository.OrderItemRepository;
import com.uniview.project0719.repository.ShoppingCartRepository;
import com.uniview.project0719.repository.UserOrderRepository;
import com.uniview.project0719.service.UserOrderService;
import com.uniview.project0719.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

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
    public ResponseData<?> createOrder(OrderParamDTO orderParamDTO) throws InterruptedException, ParseException {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findShoppingCartsByIdIsIn(orderParamDTO.getIds());
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
        address.setId(orderParamDTO.getAddressId());
        userOrder.setAddress(address);
        userOrder.setStatus(1);
        userOrderRepository.save(userOrder);
        List<OrderItem> orderItems = new ArrayList<>();
        for (ShoppingCart cart : shoppingCartList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setGoodsName(cart.getGood().getTitle());
            orderItem.setPrice(cart.getBuyPrice());
            UserOrder orderMq = new UserOrder();
            orderMq.setOrderId(orderId);
            orderItem.setUserOrder(orderMq);
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
        Map map = new HashMap<>();
        map.put("resultList", orderPage.getContent());
        map.put("total", orderPage.getTotalPages());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> getUserOrderDetail(ParamData<OrderItemDTO> paramData) {
        UserOrder userOrder = userOrderRepository.findUserOrderByOrderId(paramData.getParam().getOrderId());
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<OrderItem> itemPage = orderItemRepository.findOrderItemByUserOrder(userOrder, pageable);
        List<OrderItemResponseDTO> resultList = new ArrayList<>();
        itemPage.getContent().forEach(e -> {
            OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
            BeanUtils.copyProperties(e, orderItemResponseDTO);
            orderItemResponseDTO.setOrderId(e.getUserOrder().getOrderId());
            orderItemResponseDTO.setAddress(e.getUserOrder().getAddress());
            resultList.add(orderItemResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", itemPage.getTotalPages());
        return new ResponseData<>().success(map);
    }

    /**
     * Created by @Kurt LEE. Last Modified on 2024/7/26, 下午3:45.
     *
     */
    @Override
    public Long getTotalOrderCount() {
        return userOrderRepository.countAllOrders();
    }

    /**
     * Created by @Kurt LEE. Last Modified on 2024/7/26, 下午3:48.
     *
     * @return
     */
    @Override
    public Long getOrderCountByStatus(Integer status) {
        return userOrderRepository.countOrdersByStatus(status);
    }

}
