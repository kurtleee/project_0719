package com.uniview.project0719.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.uniview.project0719.dto.DeliverymanDTO;
import com.uniview.project0719.dto.DeliverymanResponseDTO;
import com.uniview.project0719.entity.Deliveryman;
import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.repository.DeliverymanRepository;
import com.uniview.project0719.repository.RepositoryRepository;
import com.uniview.project0719.service.DeliverymanService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 14:03
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class DeliverymanServiceImpl implements DeliverymanService {
    @Autowired
    private DeliverymanRepository deliverymanRepository;
    @Autowired
    private RepositoryRepository repositoryRepository;
    @Value("${login.salt}")
    String loginSalt;

    @Override
    public ResponseData<?> findDeliverymen(ParamData<DeliverymanDTO> paramData) {
        Repository repository = repositoryRepository.findRepositoryById(paramData.getParam().getRepositoryId());
        Specification<Deliveryman> spec = Specification.where(Specifications.deliverymanHasRepository(repository))
                .and(Specifications.deliverymanHasNameLike(paramData.getParam().getNameOrPhone()))
                .or(Specifications.deliverymanHasPhoneLike(paramData.getParam().getNameOrPhone()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Deliveryman> deliverymanPage = deliverymanRepository.findAll(spec, pageable);
        List<DeliverymanResponseDTO> resultList = new ArrayList<>();
        deliverymanPage.getContent().forEach(e -> {
            DeliverymanResponseDTO deliverymanResponseDTO = new DeliverymanResponseDTO();
            BeanUtils.copyProperties(e, deliverymanResponseDTO);
            deliverymanResponseDTO.setRepositoryId(e.getRepository().getId());
            resultList.add(deliverymanResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", deliverymanPage.getTotalPages());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findDeliverymenAvailable(ParamData<DeliverymanDTO> paramData) {
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Deliveryman> deliverymanPage = deliverymanRepository.findDeliverymenByRepositoryIsNull(pageable);
        Map map = new HashMap<>();
        map.put("resultList", deliverymanPage.getContent());
        map.put("total", deliverymanPage.getTotalPages());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> updateDeliveryman(DeliverymanDTO deliverymanDTO) {
        Deliveryman deliveryman = deliverymanRepository.findDeliverymanById(deliverymanDTO.getId());
        BeanUtils.copyProperties(deliverymanDTO, deliveryman);
        if (deliverymanDTO.getRepositoryId() == null) {
            deliveryman.setRepository(null);
        } else {
            Repository repository = repositoryRepository.findRepositoryById(deliverymanDTO.getRepositoryId());
            deliveryman.setRepository(repository);
        }
        deliverymanRepository.save(deliveryman);
        return new ResponseData<>().success();
    }

    @Override
    public ResponseData<?> createDeliveryman(DeliverymanDTO deliverymanDTO) {
        // 从redis中拿取公司密钥，下面用"密钥"代替
        if ("密钥".equals(deliverymanDTO.getKey())) {
            Deliveryman deliveryman = new Deliveryman();
            BeanUtils.copyProperties(deliverymanDTO, deliveryman);
            String md5Pw = SecureUtil.md5(deliverymanDTO.getPassword() + loginSalt);
            deliveryman.setPassword(md5Pw);
            deliveryman.setStatus(1);
            deliverymanRepository.save(deliveryman);
            return new ResponseData<>().success();
        } else {
            return new ResponseData<>().fail(ResponseEnum.NO_AUTHORITY);
        }
    }
}
