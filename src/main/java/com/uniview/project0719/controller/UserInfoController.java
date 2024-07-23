package com.uniview.project0719.controller;

import com.uniview.project0719.dto.UserInfoDto;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.service.GoodService;
import com.uniview.project0719.service.UserInfoService;

import com.uniview.project0719.service.UserOrderService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 获取用户列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getUserList")
    public Page<UserInfoDto> getUserList(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int pageSize) {
        return userInfoService.getUserList(page, pageSize);
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseData<?> deleteUser(@PathVariable Integer userId) {
        return userInfoService.deleteUser(userId);
    }

    /**
     * 获取商品列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getProductList")
    public Page<Good> getProductList(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int pageSize) {
        return goodService.getProductList(page, pageSize);
    }

    /**
     * 添加商品
     *
     * @param good
     * @return
     */
    @PostMapping("/addProduct")
    public ResponseData<?> addProduct(@RequestBody Good good) {
        return goodService.addProduct(good);
    }

    /**
     * 更新商品
     *
     * @param good
     * @return
     */
    @PutMapping("/updateProduct")
    public ResponseData<?> updateProduct(@RequestBody Good good) {
        return goodService.updateProduct(good);
    }

    /**
     * 删除商品
     *
     * @param goodId
     * @return
     */
    @DeleteMapping("/deleteProduct/{goodId}")
    public ResponseData<?> deleteProduct(@PathVariable Integer goodId) {
        return goodService.deleteProduct(goodId);
    }



    /**
     * 获取订单列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getOrderList")
    public Page<UserOrder> getOrderList(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return userOrderService.getOrderList(page, pageSize);
    }

}
