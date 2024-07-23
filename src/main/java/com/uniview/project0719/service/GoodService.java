package com.uniview.project0719.service;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.data.domain.Page;

public interface GoodService {
    /**
     * 获取商品列表
     *
     * @param page
     * @param pageSize
     */
    Page<Good> getProductList(Integer page, Integer pageSize);

    /**
     * 添加商品
     *
     * @param good
     */
    ResponseData<?> addProduct(Good good);
}
