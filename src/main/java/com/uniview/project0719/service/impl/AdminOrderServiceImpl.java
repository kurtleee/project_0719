package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.UserOrderDTO;
import com.uniview.project0719.dto.UserOrderResponseDTO;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.repository.UserOrderRepository;
import com.uniview.project0719.service.AdminOrderService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public ResponseData<?> getOrderList(ParamData<UserOrderDTO> paramData) {
        Specification<UserOrder> spec = Specification.where(Specifications.adminOrderHasOrderIdLike(paramData.getParam().getOrderIdOrAddress()))
                .or(Specifications.adminOrderHasAddressLike(paramData.getParam().getOrderIdOrAddress()))
                .and(Specifications.adminOrderHasStatus(paramData.getParam().getStatus()))
                .and(Specifications.adminOrderHasOrderDataBetween(paramData.getParam().getMinOrderDate(), paramData.getParam().getMaxOrderDate()))
                .and(Specifications.adminOrderHasOrderPriceBetween(paramData.getParam().getMinBuyCount(), paramData.getParam().getMaxBuyCount()))
                .and(Specifications.adminOrderHasBuyCountBetween(paramData.getParam().getMinBuyCount(), paramData.getParam().getMaxBuyCount()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<UserOrder> adminOrderPage = userOrderRepository.findAll(spec, pageable);
        List<UserOrderResponseDTO> resultList = new ArrayList<>();
        adminOrderPage.getContent().forEach(e->{
            UserOrderResponseDTO userOrderResponseDTO = new UserOrderResponseDTO();
            BeanUtils.copyProperties(e,userOrderResponseDTO);
            userOrderResponseDTO.setBuyCount(e.getOrderItems().size());
            userOrderResponseDTO.setDetailAddress(e.getAddress().getDetailAddress());
            resultList.add(userOrderResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList",resultList);
        map.put("total",adminOrderPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findOrderItemDetailByOrderId(String orderId) {
        return new ResponseData<>().success(userOrderRepository.findUserOrderByOrderId(orderId));
    }
}

