package com.uniview.project0719.controller;

import com.uniview.project0719.service.UserGoodService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class GoodController {
    @Autowired
    private UserGoodService userGoodService;

    /**
     * 获取商品列表
     * 功能未完成，不传参会报错
     * @param classificationId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getProductList")
    public ResponseData<?> getGoodList(@RequestParam Integer classificationId,
                                       @RequestParam Integer page,
                                       @RequestParam Integer size) {
        return userGoodService.findAllGood(classificationId, page, size);
    }

    /**
     * 通过商品ID获取商品详情
     * @param goodId
     * @return
     */
    @GetMapping("/getProductDetail")
    public ResponseData<?> getGoodDetail(@RequestParam Integer goodId) {
        return userGoodService.findGoodById(goodId);
    }
}
