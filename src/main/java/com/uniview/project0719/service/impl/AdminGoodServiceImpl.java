package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.GoodQueryDTO;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.service.AdminGoodService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @param paramData
     * @return
     */
    @Override
    public ResponseData<?> getGoodList(ParamData<GoodQueryDTO> paramData) {
        GoodQueryDTO param = paramData.getParam();//获取查询条件

        Specification<Good> spec = Specification.where(null);//初始化查询条件

        if (param.getId() != null) {//判断是否有商品id
            spec = spec.and(Specifications.adminGoodHasId(param.getId()));//添加商品id查询条件
        }
        if (param.getTitle() != null && !param.getTitle().isEmpty()) {//判断是否有商品标题
            spec = spec.and(Specifications.adminGoodHasTitleLike(param.getTitle()));//添加商品标题查询条件
        }
        if (param.getStatus() != null && param.getStatus() != 0) {//判断是否有商品状态
            spec = spec.and(Specifications.adminGoodHasStatus(param.getStatus()));//添加商品状态查询条件
        }
        if (param.getClassification() != null) {//判断是否有商品分类
            spec = spec.and(Specifications.adminGoodHasClassification(param.getClassification()));//添加商品分类查询条件
        }
        if(param.getType() != null){//判断是否有商品类型
            spec = spec.and(Specifications.adminGoodHasType(param.getType()));//添加商品类型查询条件

        }
        if (param.getMinCurrentPrice() != null && param.getMaxCurrentPrice() != null) {//判断是否有商品现价范围
            spec = spec.and(Specifications.adminGoodHasCurrentPriceBetween(param.getMinCurrentPrice(), param.getMaxCurrentPrice()));//添加商品现价范围查询条件
        }
        if (param.getMinOriginalPrice() != null && param.getMaxOriginalPrice() != null) {//判断是否有商品原价范围
            spec = spec.and(Specifications.adminGoodHasOriginalPriceBetween(param.getMinOriginalPrice(), param.getMaxOriginalPrice()));//添加商品原价范围查询条件
        }
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Good> pageResult = goodRepository.findAll(spec, pageable);
        Map map = new HashMap<>();
        map.put("resultList",pageResult.getContent());
        map.put("total",pageResult.getTotalElements());
        return new ResponseData<>().success(map);

    }

    /**
     * 获取商品详情
     */
    @Override
    public ResponseData<?> getGoodById(Integer id) {
        return new ResponseData<>().success(goodRepository.findById(id));
    }

    /**
     * 添加商品
     *
     * @param good
     */
    @Override
    public ResponseData<?> addProduct(Good good) {
        return new ResponseData<>().success(goodRepository.save(good));
    }

    /**
     * 更新商品
     *
     * @param good
     */
    @Override
    public ResponseData<?> updateProduct(Good good) {
        //判断商品是否存在
        if (goodRepository.findById(good.getId()).isPresent()) {
            return new ResponseData<>().success(goodRepository.save(good));
        } else {
            return new ResponseData<>().fail(ResponseEnum.FAIL);
        }
    }

    /**
     * 修改商品状态（上架/下架）
     *
     * @param id
     * @param status
     */
    @Override
    public ResponseData<?> updateGoodStatus(Integer id, Integer status) {
        int updatedRows = goodRepository.updateStatusById(status, id);//修改该id对应的status
        if (updatedRows > 0) {
            return new ResponseData<>().success();
        } else {
            return new ResponseData<>().fail(ResponseEnum.FAIL);
        }
    }

    /**
     * 删除多个商品或单个商品
     *
     * @param goodIds
     */
    @Override
    @Transactional
    public ResponseData<?> deleteProducts(List<Integer> goodIds) {
        if (goodIds.size() == 1) {
            // 单独删除
            goodRepository.deleteById(goodIds.get(0));
        } else {
            // 批量删除
            goodRepository.deleteAllByIdIn(goodIds);
        }
        return new ResponseData<>().success();
    }

    @Override
    public ResponseData<?> updateGoodStatusBatch(List<Integer> goodIds, Integer status) {
        // 批量修改商品状态
        for (Integer goodId : goodIds) {
            goodRepository.updateStatusById(status, goodId);
        }
        return new ResponseData<>().success();
    }
}
