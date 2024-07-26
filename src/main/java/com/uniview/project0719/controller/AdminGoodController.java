package com.uniview.project0719.controller;

import com.uniview.project0719.dto.GoodQueryDTO;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.service.AdminGoodService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "AdminGoodController", description = "商品管理控制器")
public class AdminGoodController {

    @Autowired
    private AdminGoodService adminGoodService;

    /**
     * 获取商品列表
     *
     * @param paramData
     * @return
     */
    //将原本的三个参数合并为一个参数 便于根据不同条件查询 并且将get请求改为post请求
    //将查询商品接口统一为
    @PostMapping("/getProductList")
    public ResponseData<?> getProductList(@RequestBody ParamData<GoodQueryDTO> paramData) {
        return adminGoodService.getGoodList(paramData);
    }

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getProductById/{id}")
    public ResponseData<?> getProductById(@PathVariable Integer id) {
        return adminGoodService.getGoodById(id);
    }

    /**
     * 添加商品
     *
     * @param good
     * @return
     */
    @PostMapping("/addProduct")
    public ResponseData<?> addProduct(@RequestBody Good good) {
        return adminGoodService.addProduct(good);
    }



    /**
     * 更新商品
     *
     * @param good
     * @return
     */
    @PutMapping("/updateProduct")
    public ResponseData<?> updateProduct(@RequestBody Good good) {
        return adminGoodService.updateProduct(good);
    }

    /**
     * 删除商品
     *
     * @param goodId
     * @return
     */
    @DeleteMapping("/deleteProduct/{goodId}")
    public ResponseData<?> deleteProduct(@PathVariable Integer goodId) {
        return adminGoodService.deleteProduct(goodId);
    }


}
