package com.uniview.project0719.service;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.utils.ResponseData;


public interface UserGoodService {
    /**
     * 获取商品列表
     */
    ResponseData<?> findAllGood(Integer classificationId, Integer page, Integer size);
    /**
     * 获取商品详情
     */
    ResponseData<?> findGoodById(Integer id);

    /**
     * 添加商品
     *
     * @param good
     */
    ResponseData<?> addProduct(Good good);

    /**
     * 更新商品
     * @param good
     */
    ResponseData<?> updateProduct(Good good);

    /**
     * 删除商品
     *
     * @param goodId
     */
    ResponseData<?> deleteProduct(Integer goodId);

}
