package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.DeliveryDTO;
import com.uniview.project0719.dto.DeliveryResponseDTO;
import com.uniview.project0719.entity.Delivery;
import com.uniview.project0719.repository.DeliveryRepository;
import com.uniview.project0719.service.DeliveryService;
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
    @Override
    public ResponseData<?> findAllDeliveries(ParamData<DeliveryDTO> paramData) {
        Specification<Delivery> spec = Specification.where(Specifications.deliveryHasOrderNumLike(paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId()))
                .or(Specifications.deliveryHasDeliverymanNameLike(paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId()))
                .or(Specifications.deliveryHasRepositoryNameLike(paramData.getParam().getOrderNumOrDeliverymanNameOrRepositoryId()))
                .and(Specifications.deliveryHasStatus(paramData.getParam().getStatus()))
                .and(Specifications.deliveryHasCity(paramData.getParam().getCity()))
                .and(Specifications.deliveryHasRegion(paramData.getParam().getRegion()))
                .and(Specifications.deliveryHasOrderTimeBetween(paramData.getParam().getMinOrderTime(), paramData.getParam().getMaxOrderTime()))
                .and(Specifications.deliveryHasGoodsNumBetween(paramData.getParam().getMinGoodsNum(), paramData.getParam().getMaxGoodsNum()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Delivery> deliveryPage = deliveryRepository.findAll(spec, pageable);
        List<DeliveryResponseDTO> resultList = new ArrayList<>();
        deliveryPage.getContent().forEach(e->{
            DeliveryResponseDTO deliveryResponseDTO = new DeliveryResponseDTO();
            BeanUtils.copyProperties(e,deliveryResponseDTO);
            deliveryResponseDTO.setOrderNum(e.getUserOrder().getOrderId());
            deliveryResponseDTO.setOrderTime(e.getUserOrder().getOrderDate());
            deliveryResponseDTO.setGoodsNum(e.getUserOrder().getOrderItems().size());
            deliveryResponseDTO.setDeliveryTime(Duration.between(e.getUserOrder().getOrderDate(),e.getSubmitTime()).toMinutes()+"分钟");
            deliveryResponseDTO.setDeliverymanName(e.getDeliveryman().getName());
            deliveryResponseDTO.setCity(e.getDeliveryman().getRepository().getCity());
            deliveryResponseDTO.setRegion(e.getDeliveryman().getRepository().getRegion());
            deliveryResponseDTO.setRepositoryName(e.getDeliveryman().getRepository().getName());
            resultList.add(deliveryResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList",resultList);
        map.put("total",deliveryPage.getTotalElements());
        return new ResponseData<>().success(map);
    }
}
