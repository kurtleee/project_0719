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
    private UserGoodService userGoodService;

    /**
     *
     * @param paramData
     * @return
     */
    @PostMapping("/getProductList")
    public ResponseData<?> getGoodList(@RequestBody ParamData<Good> paramData) {
        return userGoodService.findAllGood(paramData);
    }

    /**
     * 通过商品ID获取商品详情
     *
     * @param goodId
     * @return
     */
    @GetMapping("/getProductDetail")
    public ResponseData<?> getGoodDetail(@RequestParam Integer goodId) {
        return userGoodService.findGoodById(goodId);
    }
}
