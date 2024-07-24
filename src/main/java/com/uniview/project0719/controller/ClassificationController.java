package com.uniview.project0719.controller;

import com.uniview.project0719.service.ClassificationService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class ClassificationController {
    @Autowired
    private ClassificationService classificationService;

    /**
     * 分类列表查询
     *
     */
    @GetMapping("/getCategoryList")
    public ResponseData<?> findAllCategoryList(){
        return classificationService.findAllClassification();
    }
}
