package com.uniview.project0719.utils;

import com.uniview.project0719.entity.UserOrder;
import org.springframework.data.jpa.domain.Specification;

/**
 * 用于实现条件查询
 * 一个方法对应一个条件
 * 方法名命名规范：
 * 1、精确查询：实体类名+Has+条件字段名
 * 2、模糊查询：实体类名+Has+条件字段名+Like
 */
public class Specifications {

    public static Specification<UserOrder> hasStatus(Integer param) {
        return param == null ? null : (root, query, cb) -> cb.equal(root.get("status"), param);
    }

    public static Specification<UserOrder> hasOrderIdLike(String orderId) {
        return orderId == null ? null : (root, query, cb) -> cb.like(root.get("orderId"), "%" + orderId + "%");
    }
}