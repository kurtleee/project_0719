package com.uniview.project0719.repository;

import com.uniview.project0719.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, String> {

}