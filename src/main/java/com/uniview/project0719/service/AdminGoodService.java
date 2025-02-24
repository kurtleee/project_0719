package com.uniview.project0719.service;

import com.uniview.project0719.dto.GoodQueryDTO;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

import java.util.List;

/**
 * @author ：zx
 * @date ：Created in 2024/7/24 11:39
 * @description：后台管理端商品服务
 * @modified By：zyt
 * @version: $
 */
public interface AdminGoodService {
    /**
     * 获取商品列表
     */
    ResponseData<?> getGoodList(ParamData<GoodQueryDTO> paramData);

    /**
     * 获取商品详情
     */
    ResponseData<?> getGoodById(Integer id);

    /**
     * 添加商品
     *
     * @param good
     */
    ResponseData<?> addProduct(Good good);

    /**
     * 更新商品
     *
     * @param good
     */
    ResponseData<?> updateProduct(Good good);

    /**
     * 修改商品状态（上架/下架）
     *
     * @param id
     * @param status
     */
    ResponseData<?> updateGoodStatus(Integer id, Integer status);


    /**
     * 删除单个或多个商品
     *
     * @param goodIds
     */
    ResponseData<?> deleteProducts(List<Integer> goodIds);

    /**
     * 批量上架/下架商品
     */
    ResponseData<?> updateGoodStatusBatch(List<Integer> goodIds, Integer status);


}
