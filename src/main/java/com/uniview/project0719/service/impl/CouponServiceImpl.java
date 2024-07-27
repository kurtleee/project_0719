package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.Coupon;
import com.uniview.project0719.repository.CouponRepository;
import com.uniview.project0719.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author Kurt LEE
 * @created 2024/07/27 - 上午11:11
 * 优惠券的业务逻辑层
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        // 自动生成兑换码和ID
        coupon.setSerialNumber(new BigDecimal(new Random().nextInt(999999)));
        coupon.setValiUtil(Instant.now().plusSeconds(3600 * 24 * 30)); // 默认有效期30天
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon redeemCoupon(Integer couponId, Integer userId) {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();
            coupon.setUserId(userId);
            coupon.setStatus("2"); // 设为已兑现未使用
            return couponRepository.save(coupon);
        }
        return null;
    }

    @Override
    public void deleteCoupon(Integer couponId) {
        couponRepository.deleteById(couponId);
    }

    @Override
    public Coupon updateCouponStatus(Integer couponId, String status) {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();
            coupon.setStatus(status);
            return couponRepository.save(coupon);
        }
        return null;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon getCouponById(Integer couponId) {
        return couponRepository.findById(couponId).orElse(null);
    }
}

