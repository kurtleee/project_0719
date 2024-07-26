package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.ShoppingCartDTO;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.ShoppingCart;
import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.repository.ShoppingCartRepository;
import com.uniview.project0719.service.ShoppingCartService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private GoodRepository goodRepository;

    @Override
    public ResponseData<?> findAllShoppingCart(ParamData<ShoppingCart> paramData) throws ParseException {
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<ShoppingCart> shoppingCarts = shoppingCartRepository.findShoppingCartsByUserIdAndStatus(UserContext.getUserId(), 2, pageable);
        return new ResponseData<>().success(shoppingCarts);
    }

    /**
     * @param shoppingCartDTO
     * @return ResponseData<?>
     * @throws ParseException
     */
    @Override
    public ResponseData<?> addShoppingCart(ShoppingCartDTO shoppingCartDTO) throws ParseException {
        Good good = goodRepository.findGoodById(shoppingCartDTO.getGoodsId());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setGood(good);
        shoppingCart.setBuyNum(shoppingCartDTO.getBuyNum());
        Integer userId = UserContext.getUserId();
        ShoppingCart CartExt = shoppingCartRepository.findShoppingCartByGoodAndStatusAndUserId(shoppingCart.getGood(), 2, userId);
        ShoppingCart shoppingCartResult = new ShoppingCart();
        shoppingCartResult.setUserId(userId);
        shoppingCartResult.setBuyNum(CartExt == null ? shoppingCart.getBuyNum() : shoppingCart.getBuyNum() + CartExt.getBuyNum());
        shoppingCartResult.setGood(good);
        shoppingCartResult.setBuyPrice(good.getCurrentPrice());
        shoppingCartResult.setCreateTime(new Date().toInstant());
        shoppingCartResult.setTotalPrice(new BigDecimal(shoppingCartResult.getBuyNum()).multiply(shoppingCartResult.getBuyPrice()));
        shoppingCartResult.setStatus(2);
        if (CartExt == null) {
            shoppingCartRepository.save(shoppingCartResult);
        } else {
            shoppingCartResult.setId(CartExt.getId());
            shoppingCartRepository.save(shoppingCartResult);
        }
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
