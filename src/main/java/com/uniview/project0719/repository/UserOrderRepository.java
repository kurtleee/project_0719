package com.uniview.project0719.repository;

import com.uniview.project0719.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserOrderRepository extends JpaRepository<UserOrder, String>, JpaSpecificationExecutor<UserOrder> {
    UserOrder findUserOrderByOrderId(String orderId);

    //    Created by @Kurt LEE. Last Modified on 2024/7/26, 下午3:42.
    // 检索所有订单数量
    @Query("SELECT COUNT(o) FROM UserOrder o")
    Long countAllOrders();

    // 根据订单状态查找订单数量
    @Query("SELECT COUNT(o) FROM UserOrder o WHERE o.status = :status")
    Long countOrdersByStatus(Integer status);
}