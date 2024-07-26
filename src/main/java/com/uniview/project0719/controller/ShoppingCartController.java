package com.uniview.project0719.controller;

import com.uniview.project0719.entity.ShoppingCart;
import com.uniview.project0719.service.ShoppingCartService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/cart")
@Tag(name = "ShoppingCartController", description = "购物车控制器")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 查询全部购物车信息
     * @return ShoppingCart
     */
    @PostMapping("/getCartList")
    public ResponseData<?> findAllShoppingCart(@RequestBody ParamData<ShoppingCart> paramData) throws ParseException {
        return shoppingCartService.findAllShoppingCart(paramData);
    }

    /**
     * 添加购物车
     * @return shoppingCart
     */
    @PostMapping("/addToCart")
    public ResponseData<?> addShoppingCart(@RequestBody ShoppingCart shoppingCart) throws ParseException {
        return shoppingCartService.addShoppingCart(shoppingCart);
    }

    /**
     * 根据id删除购物车
     * @return ResponseData
     */
    @DeleteMapping("/deleteFromCart")
    public ResponseData<?> deleteShoppingCart(@RequestParam Integer id){
        ResponseData<?> deleteStatus = shoppingCartService.deleteShoppingCart(id);
        return deleteStatus;
    }

}
