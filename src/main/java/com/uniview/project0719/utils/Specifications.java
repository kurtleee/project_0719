package com.uniview.project0719.utils;

import com.uniview.project0719.entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.Instant;

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
    public static Specification<UserOrder> userOrderHasStatus(Integer param) {
        return param == null ? null : (root, query, cb) -> cb.equal(root.get("status"), param);
    }

    // 精确查询 根据用户id查询
    public static Specification<UserOrder> userOrderHasUserId(Integer userId) {
        return userId == null ? null : (root, query, cb) -> cb.equal(root.get("userId"), userId);
    }

    // 精确查询 商品id
    public static Specification<Good> adminGoodHasId(Integer id) {
        return id == null ? null : (Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.equal(root.get("id"), id);
    }

    // 模糊查询 商品标题
    public static Specification<Good> adminGoodHasTitleLike(String title) {
        return title == null ? null : (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    //精确查询 商品状态
    public static Specification<Good> adminGoodHasStatus(Integer status) {
        return status == null ? null : (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    //精确查询 商品分类
    public static Specification<Good> adminGoodHasClassification(Classification classification) {
        return classification == null ? null : (root, query, cb) -> cb.equal(root.get("classification"), classification);
    }

    // 查询商品现价范围
    public static Specification<Good> adminGoodHasCurrentPriceBetween(Double minPrice, Double maxPrice) {
        return minPrice == null || maxPrice == null ? null : (Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("currentPrice"), minPrice, maxPrice);
    }

    // 查询商品原价范围
    public static Specification<Good> adminGoodHasOriginalPriceBetween(Double minPrice, Double maxPrice) {
        return minPrice == null || maxPrice == null ? null : (Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("originalPrice"), minPrice, maxPrice);
    }

    // 精确查询 商品分类
    public static Specification<Good> adminGoodHasType(Type type) {
        return type == null ? null : (root, query, cb) -> cb.equal(root.get("type"), type);
    }

    //精确查询 用户商品分类
    public static Specification<Good> userGoodHasClassification(Classification classification) {
        return classification == null ? null : (root, query, cb) -> cb.equal(root.get("classification"), classification);
    }

    //精确查询 用户商品品种
    public static Specification<Good> userGoodHasType(Type type) {
        return type == null ? null : (root, query, cb) -> cb.equal(root.get("type"), type);
    }

    //模糊查询 仓库名称
    public static Specification<Repository> repositoryHasNameLike(String name) {
        return name == null ? null : (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    //模糊查询 仓库地址
    public static Specification<Repository> repositoryHasAddressLike(String address) {
        return address == null ? null : (root, query, cb) -> cb.like(root.get("address"), "%" + address + "%");
    }

    //精确查询 仓库城市
    public static Specification<Repository> repositoryHasCity(String city) {
        return city == null ? null : (root, query, cb) -> cb.equal(root.get("city"), city);
    }

    //精确查询 仓库区域
    public static Specification<Repository> repositoryHasRegion(String region) {
        return region == null ? null : (root, query, cb) -> cb.equal(root.get("region"), region);
    }

    //范围查询 仓库面积
    public static Specification<Repository> repositoryHasAreaBetween(Integer minArea, Integer maxArea) {
        return minArea == null || maxArea == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("area"), minArea, maxArea);
    }

    //范围查询 仓库关联小区数
    public static Specification<Repository> repositoryHasCommunityBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("communities")), min, max);
    }

    //范围查询 仓库关联骑手数
    public static Specification<Repository> repositoryHasDeliverymanBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("deliverymen")), min, max);
    }

    //范围查询 仓库关联分拣员数
    public static Specification<Repository> repositoryHasSorterBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<Repository> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("sorters")), min, max);
    }
    // 精确查询 配送员所属仓库
    public static Specification<Deliveryman> deliverymanHasRepository(Repository repository) {
        return repository == null ? null : (root, query, cb) -> cb.equal(root.get("repository"), repository);
    }
    //模糊查询 配送员手机号
    public static Specification<Deliveryman> deliverymanHasPhoneLike(String phone) {
        return phone == null ? null : (root, query, cb) -> cb.like(root.get("phone"), "%" + phone + "%");
    }
    //模糊查询 配送员手机号
    public static Specification<Deliveryman> deliverymanHasNameLike(String name) {
        return name == null ? null : (root, query, cb) -> cb.like(root.get("phone"), "%" + name + "%");
    }
    // 精确查询 配送员所属仓库
    public static Specification<Sorter> sorterHasRepository(Repository repository) {
        return repository == null ? null : (root, query, cb) -> cb.equal(root.get("repository"), repository);
    }
    //模糊查询 分拣员手机号
    public static Specification<Sorter> sorterHasPhoneLike(String phone) {
        return phone == null ? null : (root, query, cb) -> cb.like(root.get("phone"), "%" + phone + "%");
    }
    //模糊查询 分拣员手机号
    public static Specification<Sorter> sorterHasNameLike(String name) {
        return name == null ? null : (root, query, cb) -> cb.like(root.get("phone"), "%" + name + "%");
    }
    // 精确查询 配送员所属仓库
    public static Specification<Community> communityHasRepository(Repository repository) {
        return repository == null ? null : (root, query, cb) -> cb.equal(root.get("repository"), repository);
    }
    //模糊查询 小区名称
    public static Specification<Community> communityHasNameLike(String name) {
        return name == null ? null : (root, query, cb) -> cb.like(root.get("phone"), "%" + name + "%");
    }
    //模糊查询 订单编号
    public static Specification<UserOrder> adminOrderHasOrderIdLike(String orderId) {
        return orderId == null ? null : (root, query, cb) -> cb.like(root.get("orderId"), "%" + orderId + "%");
    }

    //模糊查询 收货地址
    public static Specification<UserOrder> adminOrderHasAddressLike(String address) {
        return address == null ? null : (root, query, cb) -> cb.like(root.get("address").get("detailAddress"), "%" + address + "%");
    }

    //精确查询 订单状态
    public static Specification<UserOrder> adminOrderHasStatus(Integer status) {
        return status == null ? null : (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    //范围查询 下单时间
    public static Specification<UserOrder> adminOrderHasOrderDataBetween(Instant min, Instant max) {
        return min == null || max == null ? null : (Root<UserOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("orderDate"), min, max);
    }

    //范围查询 订单商品金额范围
    public static Specification<UserOrder> adminOrderHasOrderPriceBetween(BigDecimal min, BigDecimal max) {
        return min == null || max == null ? null : (Root<UserOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(root.get("orderPrice"), min, max);
    }

    //范围查询 订单商品数量范围
    public static Specification<UserOrder> adminOrderHasBuyCountBetween(Integer min, Integer max) {
        return min == null || max == null ? null : (Root<UserOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                cb.between(cb.size(root.get("orderItems")), min, max);
    }
    // 模糊查询 管理员账号名
    public static Specification<AdministratorInfo> adminInfoHasAccountLike(String account) {
        return account == null ? null : (root, query, cb) -> cb.like(root.get("account"), "%" + account + "%");
    }
    // 精确查询 管理员账号状态
    public static Specification<AdministratorInfo> adminInfoHasStatus(Integer status) {
        return status == null ? null : (root, query, cb) -> cb.equal(root.get("status"), status);
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

    //模糊查询 用户Id
    public static Specification<UserInfo> userInfoHasUserIdLike(Integer id) {
        if (id == null) {
            return null;  // 或者返回 cb.conjunction()，表示没有特定条件
        }
        String idStr = id.toString();
        return (root, query, cb) -> cb.like(root.get("id").as(String.class), "%" + idStr + "%");
    }

    //模糊查询 用户微信号
    public static Specification<UserInfo> userInfoHasWxIdLike(String wxId) {
        return wxId == null ? null : (root, query, cb) -> cb.like(root.get("wxId"), "%" + wxId + "%");
    }

    //模糊查询 用户昵称
    public static Specification<UserInfo> userInfoHasNickNameLike(String nickName) {
        return nickName == null ? null : (root, query, cb) -> cb.like(root.get("nickName"), "%" + nickName + "%");
    }

}