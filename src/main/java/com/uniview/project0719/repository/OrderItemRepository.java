package com.uniview.project0719.repository;

import com.uniview.project0719.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>, JpaSpecificationExecutor<OrderItem> {
    Page<OrderItem> findOrderItemByOrderId(String orderId, Pageable pageable);
}
