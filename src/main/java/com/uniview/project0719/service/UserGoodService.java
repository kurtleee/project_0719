package com.uniview.project0719.service;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;


public interface UserGoodService {
    /**
     * 获取商品列表
     */
    ResponseData<?> findAllGood(ParamData<Good> paramData);
    /**
     * 获取商品详情
     */
    ResponseData<?> findGoodById(Integer id);
}
