package com.uniview.project0719.utils;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.UserOrder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于实现条件查询
 * 一个方法对应一个条件
 * 方法名命名规范：
 * 1、精确查询：实体类名+Has+条件字段名
 * 2、模糊查询：实体类名+Has+条件字段名+Like
 * 3、范围查询：实体类名+Has+条件字段名+Between
 */
public class Specifications {

    // 精确查询 用户订单状态
    public static Specification<UserOrder> UserOrderHasStatus(Integer param) {
        return param == null ? null : (root, query, cb) -> cb.equal(root.get("status"), param);
    }

    // 精确查询 根据用户id查询
    public static Specification<UserOrder> UserOrderHasUserId(Integer userId) {
        return userId == null ? null : (root, query, cb) -> cb.equal(root.get("userId"), userId);
    }

    // 精确查询 商品id
    public static Specification<Good> AdminGoodHasId(Integer id) {
        return id == null ? null : (Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.equal(root.get("id"), id);
    }

    // 模糊查询 商品标题
    public static Specification<Good> AdminGoodHasTitleLike(String title) {
        return title == null ? null : (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    //精确查询 商品状态
    public static Specification<Good> AdminGoodHasStatus(Integer status) {
        return status == null ? null : (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    //精确查询 商品分类
    public static Specification<Good> AdminGoodHasClassificationId(Integer classificationId) {
        return classificationId == null ? null : (root, query, cb) -> cb.equal(root.get("classificationId"), classificationId);
    }

    // 查询商品现价范围
    public static Specification<Good> AdminGoodHasCurrentPriceBetween(Double minPrice, Double maxPrice) {
        return minPrice == null || maxPrice == null ? null : (Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("currentPrice"), minPrice, maxPrice);
    }

    // 查询商品原价范围
    public static Specification<Good> AdminGoodHasOriginalPriceBetween(Double minPrice, Double maxPrice) {
        return minPrice == null || maxPrice == null ? null : (Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("originalPrice"), minPrice, maxPrice);
    }

    // 精确查询 商品分类
    public static Specification<Good> AdminGoodHasTypeId(Integer typeId) {
        return typeId == null ? null : (root, query, cb) -> cb.equal(root.get("typeId"), typeId);
    }
}