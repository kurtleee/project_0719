package com.uniview.project0719.utils;

import com.uniview.project0719.entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
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
    public static Specification<Good> AdminGoodHasClassification(Classification classification) {
        return classification == null ? null : (root, query, cb) -> cb.equal(root.get("classification"), classification);
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

    //精确查询 用户商品分类
    public static Specification<Good> UserGoodHasClassification(Classification classification) {
        return classification == null ? null : (root, query, cb) -> cb.equal(root.get("classification"), classification);
    }

    //精确查询 用户商品品种
    public static Specification<Good> UserGoodHasType(Type type) {
        return type == null ? null : (root, query, cb) -> cb.equal(root.get("type"), type);
    }

    //模糊查询 仓库名称
    public static Specification<Repository> RepositoryHasNameLike(String name) {
        return name == null ? null : (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    //模糊查询 仓库名称
    public static Specification<Repository> RepositoryHasAddressLike(String address) {
        return address == null ? null : (root, query, cb) -> cb.like(root.get("address"), "%" + address + "%");
    }

    //精确查询 仓库城市
    public static Specification<Repository> RepositoryHasCity(String city) {
        return city == null ? null : (root, query, cb) -> cb.equal(root.get("city"), city);
    }

    //精确查询 仓库区域
    public static Specification<Repository> RepositoryHasRegion(String region) {
        return region == null ? null : (root, query, cb) -> cb.equal(root.get("region"), region);
    }

    //范围查询 仓库面积
    public static Specification<Repository> RepositoryHasAreaBetween(Integer minArea, Integer maxArea) {
        return minArea == null || maxArea == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("area"), minArea, maxArea);
    }

    //范围查询 仓库关联小区数
    public static Specification<Repository> RepositoryHasCommunityBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("communities")), min, max);
    }

    //范围查询 仓库关联骑手数
    public static Specification<Repository> RepositoryHasDeliverymanBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("deliverymen")), min, max);
    }

    //范围查询 仓库关联分拣员数
    public static Specification<Repository> RepositoryHasSorterBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("sorters")), min, max);
    }

    //模糊查询 分拣监控订单编号查询
    public static Specification<Sort> sortHasOrderNumLike(String orderNum) {
        return orderNum == null ? null : (root, query, cb) -> cb.like(root.get("userOrder").get("orderId"), "%" + orderNum + "%");
    }

    //模糊查询 分拣监控分拣员姓名查询
    public static Specification<Sort> sortHasSorterNameLike(String sorterName) {
        return sorterName == null ? null : (root, query, cb) -> cb.like(root.get("sorter").get("name"), "%" + sorterName + "%");
    }

    //模糊查询 分拣监控仓库名称查询
    public static Specification<Sort> sortHasRepositoryNameLike(String repositoryName) {
        return repositoryName == null ? null : (root, query, cb) -> cb.like(root.get("sorter").get("repository").get("name"), "%" + repositoryName + "%");
    }

    //精确查询 分拣状态
    public static Specification<Sort> sortHasStatus(Integer status) {
        return status == null ? null : (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    //精确查询 分拣城市
    public static Specification<Sort> sortHasCity(String city) {
        return city == null ? null : (root, query, cb) -> cb.equal(root.get("sorter").get("repository").get("city"), city);
    }

    //精确查询 分拣地区
    public static Specification<Sort> sortHasRegion(String region) {
        return region == null ? null : (root, query, cb) -> cb.equal(root.get("sorter").get("repository").get("region"), region);
    }

    //时间范围查询 分拣下单时间范围
    public static Specification<Sort> sortHasOrderTimeBetween(Instant min, Instant max) {
        return min == null || max == null ? null : (Root<Sort> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("userOrder").get("orderDate"), min, max);
    }

    //范围查询 分拣商品数量范围
    public static Specification<Sort> sortHasGoodsNumBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Sort> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("userOrder").get("orderItems")), min, max);
    }

    //模糊查询 配送监控订单编号查询
    public static Specification<Delivery> deliveryHasOrderNumLike(String orderNum) {
        return orderNum == null ? null : (root, query, cb) -> cb.like(root.get("userOrder").get("orderId"), "%" + orderNum + "%");
    }

    //模糊查询 配送监控分拣员姓名查询
    public static Specification<Delivery> deliveryHasDeliverymanNameLike(String deliverymanName) {
        return deliverymanName == null ? null : (root, query, cb) -> cb.like(root.get("deliveryman").get("name"), "%" + deliverymanName + "%");
    }

    //模糊查询 配送监控仓库名称查询
    public static Specification<Delivery> deliveryHasRepositoryNameLike(String repositoryName) {
        return repositoryName == null ? null : (root, query, cb) -> cb.like(root.get("deliveryman").get("repository").get("name"), "%" + repositoryName + "%");
    }

    //精确查询 配送状态
    public static Specification<Delivery> deliveryHasStatus(Integer status) {
        return status == null ? null : (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    //精确查询 配送城市
    public static Specification<Delivery> deliveryHasCity(String city) {
        return city == null ? null : (root, query, cb) -> cb.equal(root.get("deliveryman").get("repository").get("city"), city);
    }

    //精确查询 配送地区
    public static Specification<Delivery> deliveryHasRegion(String region) {
        return region == null ? null : (root, query, cb) -> cb.equal(root.get("deliveryman").get("repository").get("region"), region);
    }

    //时间范围查询 配送下单时间范围
    public static Specification<Delivery> deliveryHasOrderTimeBetween(Instant min, Instant max) {
        return min == null || max == null ? null : (Root<Delivery> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("userOrder").get("orderDate"), min, max);
    }

    //范围查询 配送商品数量范围
    public static Specification<Delivery> deliveryHasGoodsNumBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Delivery> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("userOrder").get("orderItems")), min, max);
    }
}