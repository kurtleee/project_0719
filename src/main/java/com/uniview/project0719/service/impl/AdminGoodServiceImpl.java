package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.GoodQueryDTO;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.service.AdminGoodService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author ：zx
 * @date ：Created in 2024/7/24 11:39
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class AdminGoodServiceImpl implements AdminGoodService {

    @Autowired
    private GoodRepository goodRepository;

    /**
     * 获取商品列表
     * 此方法查询了商品的id、标题、状态、分类、现价范围、原价范围
     * @param paramData
     * @return
     *
     */
    @Override
    public ResponseData<?> getGoodList(ParamData<GoodQueryDTO> paramData) {
        GoodQueryDTO param = paramData.getParam();//获取查询条件
        Specification<Good> spec = Specification.where(null);//初始化查询条件

        if (param.getId() != null) {//判断是否有商品id
            spec = spec.and(Specifications.AdminGoodHasIdEqual(param.getId()));//添加商品id查询条件
        }
        if (param.getTitle() != null && !param.getTitle().isEmpty()) {//判断是否有商品标题
            spec = spec.and(Specifications.AdminGoodHasTitleLike(param.getTitle()));//添加商品标题查询条件
        }
        if (param.getStatus() != null && param.getStatus() != 0) {//判断是否有商品状态
            spec = spec.and(Specifications.AdminGoodHasStatus(param.getStatus()));//添加商品状态查询条件
        }
        if (param.getClassificationId() != null && param.getClassificationId() != 0) {//判断是否有商品分类
            spec = spec.and(Specifications.AdminGoodHasClassificationId(param.getClassificationId()));//添加商品分类查询条件
        }
        if (param.getMinCurrentPrice() != null && param.getMaxCurrentPrice() != null) {//判断是否有商品现价范围
            spec = spec.and(Specifications.AdminGoodHasCurrentPriceBetween(param.getMinCurrentPrice(), param.getMaxCurrentPrice()));//添加商品现价范围查询条件
        }
        if (param.getMinOriginalPrice() != null && param.getMaxOriginalPrice() != null) {//判断是否有商品原价范围
            spec = spec.and(Specifications.AdminGoodHasOriginalPriceBetween(param.getMinOriginalPrice(), param.getMaxOriginalPrice()));//添加商品原价范围查询条件
        }

        Pageable pageable = PageRequest.of(paramData.getPage(), paramData.getSize());
        Page<Good> pageResult = goodRepository.findAll(spec, pageable);

        return new ResponseData<>().success(pageResult);

    }

    /**
     * 获取商品详情（未完善）
     */
    @Override
    public ResponseData<?> getGoodById(Integer id) {
        return null;
    }

    /**
     * 添加商品（未完善）
     *
     * @param good
     */
    @Override
    public ResponseData<?> addProduct(Good good) {
        return null;
    }

    /**
     * 更新商品（未完善）
     *
     * @param good
     */
    @Override
    public ResponseData<?> updateProduct(Good good) {
        return null;
    }

    /**
     * 删除商品（未完善）
     *
     * @param goodId
     */
    @Override
    public ResponseData<?> deleteProduct(Integer goodId) {
        return null;
    }

    /**
     * 修改商品状态（未完善）
     *
     * @param goodId
     */
    @Override
    public ResponseData<?> updateGoodStatus(Integer goodId) {
        return null;
    }
}
