package com.uniview.project0719.service;

import com.uniview.project0719.entity.Coupon;
import com.uniview.project0719.utils.ResponseData;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kurt LEE
 * @created 2024/07/27 - 上午11:11
 * 优惠券的业务逻辑层
 */
public interface CouponService {
    ResponseData<Coupon> createCoupon(Coupon coupon);
    ResponseData<Coupon> redeemCoupon(BigDecimal serialNumber, Integer userId);
    ResponseData<Void> deleteCoupon(Integer couponId);
    ResponseData<Coupon> updateCouponStatus(Integer couponId, String status);
    ResponseData<List<Coupon>> getAllCoupons();
    ResponseData<Coupon> getCouponBySerialNumber(BigDecimal serialNumber);
}

