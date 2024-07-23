package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.service.GoodService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodRepository goodRepository;
    /**
     * 获取商品列表
     *
     * @param page
     * @param pageSize
     */
    @Override
    public Page<Good> getProductList(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Good> GoodPage = goodRepository.findAll(pageable);
        return GoodPage.map(Good -> {
            Good dto = new Good();
            dto.setId(Good.getId());
            dto.setTitle(Good.getTitle());
            dto.setCurrentPrice(Good.getCurrentPrice());
            dto.setOriginalPrice(Good.getOriginalPrice());
            dto.setSaleCount(Good.getSaleCount());
            dto.setImgSrc(Good.getImgSrc());
            return dto;
        });
    }

    /**
     * 添加商品
     * 注意：这里返回的是Good类，不是goodsId
     * @param good
     */
    @Override
    public ResponseData<?> addProduct(Good good) {
        Good good1 = new Good();
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
    }
}
