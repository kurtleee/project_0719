package com.uniview.project0719.service;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.utils.ResponseData;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface GoodService {
    /**
     * 获取商品列表
     */
    ResponseData<?> findAllGood(Integer classificationId, Integer page, Integer size);

    /**
     * 获取商品详情
     */
    ResponseData<?> findGoodById(Integer id);
}
