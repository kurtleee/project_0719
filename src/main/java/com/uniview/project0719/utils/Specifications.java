package com.uniview.project0719.utils;

import com.uniview.project0719.entity.UserOrder;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class Specifications {
    public static Specification<UserOrder> hasStatus(Integer status) {
        return status == null ? null : (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<UserOrder> hasOrderIdLike(String orderId) {
        return orderId == null ? null : (root, query, cb) -> cb.like(root.get("orderId"), "%" + orderId + "%");
    }
}