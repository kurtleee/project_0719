package com.uniview.project0719.controller;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.service.UserGoodService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class UserGoodController {
    @Autowired
    private UserGoodService goodService;

    /**
     * 获取商品列表
     * 功能未完成，不传参会报错
     * @param paramData
     * @return
     */
    @GetMapping("/getProductList")
    public ResponseData<?> getGoodList(@RequestBody ParamData<Good> paramData) {
        return goodService.findAllGood(paramData.getParam().getClassificationId(), paramData.getPage(), paramData.getSize());
    }
    /**
     * 通过商品ID获取商品详情
     * @param goodId
     * @return
     */
    @GetMapping("/getProductDetail")
    public ResponseData<?> getGoodDetail(@RequestParam Integer goodId) {
        return goodService.findGoodById(goodId);
    }
}
