package com.uniview.project0719.service;

import com.uniview.project0719.entity.ShoppingCart;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;


public interface ShoppingCartService {
    /**
     * 获取购物车列表
     * @return ResponseData
     */
     ResponseData<?> findAllShoppingCart() throws ParseException;

    /**
     * 添加购物车
     * @return ResponseData
     */
    ResponseData<?> addShoppingCart(ShoppingCart shoppingCart,String jwt) throws ParseException;

    /**
     * 删除购物车
     * @return ResponseData
     */
    ResponseData<?> deleteShoppingCart(Integer id);

}
