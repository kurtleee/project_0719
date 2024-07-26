package com.uniview.project0719.service.impl;


import com.uniview.project0719.entity.Good;
import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.service.UserGoodService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserGoodServiceImpl implements UserGoodService {
    @Autowired
    private GoodRepository goodRepository;

    /**
     * @param paramData
     * @return
     */
    @Override
    public ResponseData<?> findAllGood(ParamData<Good> paramData) {
        Specification<Good> spec = Specification.where(Specifications.UserGoodHasClassification(paramData.getParam().getClassification())).and(Specifications.UserGoodHasType(paramData.getParam().getType()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Good> goodPage = goodRepository.findAll(spec, pageable);
        Map map = new HashMap<>();
        map.put("resultList",goodPage.getContent());
        map.put("total",goodPage.getTotalPages());
        return new ResponseData<>().success(map);
    }


    /**
     * 查询商品详情
     *
     * @param id
     * @return
     */
    @Override
    public ResponseData<?> findGoodById(Integer id) {
        return new ResponseData<>().success(goodRepository.findGoodById(id));
    }
}
