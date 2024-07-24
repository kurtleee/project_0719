package com.uniview.project0719.repository;

import com.uniview.project0719.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserOrderRepository extends JpaRepository<UserOrder,String>, JpaSpecificationExecutor<UserOrder> {
}