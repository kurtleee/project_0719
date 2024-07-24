package com.uniview.project0719.repository;

import com.uniview.project0719.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
}
