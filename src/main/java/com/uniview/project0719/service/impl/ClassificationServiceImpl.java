package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.Classification;
import com.uniview.project0719.repository.ClassificationRepository;
import com.uniview.project0719.service.ClassificationService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationRepository classificationRepository;

    /**
     * 查询分类列表
     */
    @Override
    public ResponseData<?> findAllClassification() {
        List<Classification> all = classificationRepository.findAll();
        return new ResponseData<>().success(all);
    }
}
