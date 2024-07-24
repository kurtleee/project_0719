package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.Address;
import com.uniview.project0719.entity.ShoppingCart;
import com.uniview.project0719.repository.AddressRepository;
import com.uniview.project0719.repository.GoodRepository;
import com.uniview.project0719.repository.ShoppingCartRepository;
import com.uniview.project0719.service.ShoppingCartService;
import com.uniview.project0719.utils.JWTUtil;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private GoodRepository goodRepository;

    /**
     * 查找所有购物车
     * 没有实现的功能
     *
     */
    @Override
    public ResponseData<?> findAllShoppingCart() throws ParseException {
        Integer userId = UserContext.getUserId();
        Optional<Address> address = addressRepository.findById(userId);
        Optional<ShoppingCart> shoppingCarts = shoppingCartRepository.findById(userId);
        List<Address> addressList = address.stream().toList();
        List<ShoppingCart> shoppingCartList = shoppingCarts.stream().toList();
        Map resMap = new HashMap<>();
        resMap.put("addressList",addressList);
        resMap.put("shoppingCartList",shoppingCartList);
        return new ResponseData<>().success(resMap);
    }

    /**
     * 没有实现的功能
     * 用户添加购物车
     * 1：查询商品是否添加过
     *      添加过：修改
     *      没有添加过新增
     *
     */
    @Override
    public ResponseData<?> addShoppingCart(ShoppingCart shoppingCart,String jwt) throws ParseException {
        //1:查询数据是否被添加过商品
        Map userInfo = JWTUtil.getJWTUserInfo(jwt);
        Long userId = (Long) userInfo.get("userId");
        shoppingCart.setUserId(userId.intValue());
        ShoppingCart shoppingCartExt = shoppingCartRepository.save(shoppingCart);
        ShoppingCart shoppingCartResult = new ShoppingCart();
        shoppingCartResult.setUserId(userId.intValue());
        shoppingCartResult.setBuyNum(shoppingCartExt == null ? shoppingCart.getBuyNum() : shoppingCart.getBuyNum() + shoppingCartExt.getBuyNum());
        shoppingCartResult.setGoodsId(shoppingCart.getGoodsId());
        //查询商品单价，需要根据商品id查询，未实现
        //---------------------------------------------止步
        //2:查询书籍单价
        //3:做条件判断 如果购物车为空新增，若购物车有则新增
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
