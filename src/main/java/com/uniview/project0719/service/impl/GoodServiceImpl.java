package com.uniview.project0719.service.impl;

import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.service.GoodService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
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
}
