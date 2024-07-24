package com.uniview.project0719.service;

import com.uniview.project0719.utils.ResponseData;

public interface ClassificationService {
    /**
     * 查询分类列表
     */
    ResponseData<?> findAllClassification();
}
