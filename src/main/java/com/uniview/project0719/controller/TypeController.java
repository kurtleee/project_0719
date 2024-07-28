package com.uniview.project0719.controller;

import com.uniview.project0719.service.TypeService;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
@Tag(name = "类型接口", description = "TypeController")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/getTypeList")
    public ResponseData<?> findAllType(){
        return typeService.findAllType();
    }

}
