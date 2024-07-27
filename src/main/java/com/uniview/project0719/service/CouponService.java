package com.uniview.project0719.service;

import com.uniview.project0719.entity.Coupon;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kurt LEE
 * @created 2024/07/27 - 上午11:11
 * 优惠券的业务逻辑层
 */
public interface CouponService {
    Coupon createCoupon(Coupon coupon);
    Coupon redeemCoupon(BigDecimal serialNumber, Integer userId);
    void deleteCoupon(Integer couponId);
    Coupon updateCouponStatus(Integer couponId, String status);
    List<Coupon> getAllCoupons();
//    Coupon getCouponById(Integer couponId);
    Coupon getCouponBySerialNumber(BigDecimal serialNumber);
}

