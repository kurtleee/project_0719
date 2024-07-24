package com.uniview.project0719.service.impl;


import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.service.GoodService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.utils.ResponseEnum;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodRepository goodRepository;

    /**
     * 查询所有商品
     * @param classificationId
     * @param page
     * @param size
     * @return
     */
    @Override
    public ResponseData<?> findAllGood(Integer classificationId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseData<>().success(goodRepository.findAllByClassificationId(classificationId,pageable));
    }


    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @Override
    public ResponseData<?> findGoodById(Integer id) {
        return new ResponseData<>().success(goodRepository.findById(id));

    }

    /**
     * 添加商品
     * 注意：这里返回的是Good类，不是goodsId
     * @param good
     */
    @Override
    public ResponseData<?> addProduct(Good good) {
        Good saveGood = goodRepository.save(good);
        return new ResponseData<>().success(saveGood);
    }

    /**
     * 更新商品
     *
     * @param good
     */

    @Override
    public ResponseData<?> updateProduct(Good good) {
        Good good1 = goodRepository.findById(good.getId()).orElse(null);
        if (good1 != null) {
            good1.setTitle(good.getTitle());
            good1.setCurrentPrice(good.getCurrentPrice());
            good1.setOriginalPrice(good.getOriginalPrice());
            good1.setDescription(good.getDescription());
            good1.setTag(good.getTag());
            good1.setImgSrc(good.getImgSrc());
            good1.setSpecification(good.getSpecification());
            good1.setClassificationId(good.getClassificationId());
            Good saveGood = goodRepository.save(good1);
            return new ResponseData<>().success(saveGood);
        } else {
            return new ResponseData<>().fail(ResponseEnum.FAIL);
        }
    }

    /**
     * 删除商品
     *
     * @param goodId
     */
    @Override
    public ResponseData<?> deleteProduct(Integer goodId) {
        goodRepository.deleteById(goodId);
        return new ResponseData<>().success();
    }
}
