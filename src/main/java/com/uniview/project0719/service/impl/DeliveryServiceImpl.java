package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.DeliveryDTO;
import com.uniview.project0719.dto.DeliveryResponseDTO;
import com.uniview.project0719.dto.OrderItemResponseDTO;
import com.uniview.project0719.entity.Delivery;
import com.uniview.project0719.repository.DeliveryRepository;
import com.uniview.project0719.repository.DeliverymanRepository;
import com.uniview.project0719.service.DeliveryService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.service
 * @Project：project_0719
 * @name：DeliveryServiceImpl
 * @Date：2024/7/27 16:13
 * @Filename：DeliveryServiceImpl
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliverymanRepository deliverymanRepository;

    @Override
    public ResponseData<?> findAllDeliveries(ParamData<DeliveryDTO> paramData) {
        Specification<Delivery> spec = Specification.where(Specifications.deliveryHasStatus(paramData.getParam().getStatus()))
                .and(Specifications.deliveryHasCity(paramData.getParam().getCity()))
                .and(Specifications.deliveryHasRegion(paramData.getParam().getRegion()))
                .and(Specifications.deliveryHasOrderTimeBetween(paramData.getParam().getMinOrderTime(), paramData.getParam().getMaxOrderTime()))
                .and(Specifications.deliveryHasGoodsNumBetween(paramData.getParam().getMinGoodsNum(), paramData.getParam().getMaxGoodsNum()));
        if (!"".equals(paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId()) && paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId() != null) {
            spec = spec.and((Specifications.deliveryHasOrderNumLike(paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId()))
                    .or(Specifications.deliveryHasDeliverymanNameLike(paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId()))
                    .or(Specifications.deliveryHasRepositoryNameLike(paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId())));
        }
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Delivery> deliveryPage = deliveryRepository.findAll(spec, pageable);
        List<DeliveryResponseDTO> resultList = new ArrayList<>();
        deliveryPage.getContent().forEach(e -> {
            DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO();
            BeanUtils.copyProperties(e, deliveryResponseDTO);
            deliveryResponseDTO.setOrderNum(e.getUserOrder().getOrderId());
            deliveryResponseDTO.setOrderTime(e.getUserOrder().getOrderDate());
            deliveryResponseDTO.setGoodsNum(e.getUserOrder().getOrderItems().size());
            deliveryResponseDTO.setDeliveryTime(Duration.between(e.getUserOrder().getOrderDate(), e.getSubmitTime()).toMinutes() + "分钟");
            deliveryResponseDTO.setDeliverymanName(e.getDeliveryman().getName());
            deliveryResponseDTO.setCity(e.getDeliveryman().getRepository().getCity());
            deliveryResponseDTO.setRegion(e.getDeliveryman().getRepository().getRegion());
            deliveryResponseDTO.setRepositoryName(e.getDeliveryman().getRepository().getName());
            resultList.add(deliveryResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", deliveryPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findDeliveriesByRepository(ParamData<DeliveryDTO> paramData) throws ParseException {
        Integer deliverymanId = UserContext.getUserId();
        Integer repositoryId = deliverymanRepository.findDeliverymanById(deliverymanId).getRepository().getId();
        Specification<Delivery> spec = Specification.where(Specifications.deliveryHasRepositoryId(repositoryId)).and(Specifications.deliveryHasStatus(2));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Delivery> deliveryPage = deliveryRepository.findAll(spec, pageable);
        List<DeliveryResponseDTO> resultList = new ArrayList<>();
        deliveryPage.getContent().forEach(e -> {
            DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO();
            BeanUtils.copyProperties(e, deliveryResponseDTO);
            deliveryResponseDTO.setOrderNum(e.getUserOrder().getOrderId());
            deliveryResponseDTO.setGoodsNum(e.getUserOrder().getOrderItems().size());
            deliveryResponseDTO.setAddress(e.getUserOrder().getAddress().getDetailAddress());
            deliveryResponseDTO.setPhone(e.getUserOrder().getAddress().getPhone());
            deliveryResponseDTO.setReceiver(e.getUserOrder().getAddress().getReceiver());
            resultList.add(deliveryResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", deliveryPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findDeliveryByStatus(ParamData<DeliveryDTO> paramData) throws ParseException {
        Specification<Delivery> spec = Specification.where(Specifications.deliveryHasDeliverymanId(UserContext.getUserId())).and(Specifications.deliveryHasStatus(paramData.getParam().getStatus()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Delivery> deliveryPage = deliveryRepository.findAll(spec, pageable);
        List<DeliveryResponseDTO> resultList = new ArrayList<>();
        deliveryPage.getContent().forEach(e -> {
            DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO();
            BeanUtils.copyProperties(e, deliveryResponseDTO);
            deliveryResponseDTO.setOrderNum(e.getUserOrder().getOrderId());
            deliveryResponseDTO.setGoodsNum(e.getUserOrder().getOrderItems().size());
            deliveryResponseDTO.setAddress(e.getUserOrder().getAddress().getDetailAddress());
            deliveryResponseDTO.setPhone(e.getUserOrder().getAddress().getPhone());
            deliveryResponseDTO.setReceiver(e.getUserOrder().getAddress().getReceiver());
            List<OrderItemResponseDTO> orderItems = new ArrayList<>();
            e.getUserOrder().getOrderItems().forEach(o -> {
                OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
                BeanUtils.copyProperties(o, orderItemResponseDTO);
                orderItems.add(orderItemResponseDTO);
            });
            deliveryResponseDTO.setOrderItems(orderItems);
            resultList.add(deliveryResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", deliveryPage.getTotalElements());
        return new ResponseData<>().success(map);
    }
}
