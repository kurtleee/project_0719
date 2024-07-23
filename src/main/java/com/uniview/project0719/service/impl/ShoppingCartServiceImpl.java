package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.ShoppingCart;
import com.uniview.project0719.repository.ShoppingCartRepository;
import com.uniview.project0719.service.ShoppingCartService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**
     * 查找所有购物车
     */
    @Override
    public ResponseData<?> findAllShoppingCart() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        return new ResponseData<>().success(shoppingCarts);
    }

    /**
     * 添加购物车
     */
    @Override
    public ResponseData<?> addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
        return new ResponseData<>().success();
    }

    /**
     * 根据购物车id删除购物车
     */
    @Override
    public ResponseData<?> deleteShoppingCart(Integer id) {
        shoppingCartRepository.deleteById(id);
        return new ResponseData<>().success();
    }
}
