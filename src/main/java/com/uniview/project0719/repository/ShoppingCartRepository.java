package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    List<ShoppingCart> findShoppingCartsByUserIdAndStatus(Integer userId, Integer status);
    List<ShoppingCart> findShoppingCartsByIdIsIn(Collection<Integer> id);
    ShoppingCart findShoppingCartByGoodAndStatusAndUserId(Good good,Integer status,Integer userId);
}
