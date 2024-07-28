package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.Coupon;
import com.uniview.project0719.repository.CouponRepository;
import com.uniview.project0719.service.CouponService;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
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
    public ResponseData<Coupon> createCoupon(Coupon coupon) {
        coupon.setSerialNumber(new BigDecimal(new Random().nextInt(999999)));
        coupon.setValiUtil(Instant.now().plusSeconds(3600 * 24 * 30)); // 默认有效期30天
        coupon.setStatus("1"); // 设置初始状态为未兑现
        Coupon savedCoupon = couponRepository.save(coupon);
        return new ResponseData<Coupon>().success(savedCoupon);
    }

    @Override
    public ResponseData<Coupon> redeemCoupon(BigDecimal serialNumber, Integer userId) {
        Optional<Coupon> optionalCoupon = couponRepository.findBySerialNumber(serialNumber);
        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();
            if (coupon.getStatus() == null || coupon.getStatus().equals("1")) {
                coupon.setUserId(userId);
                coupon.setStatus("2"); // 设为已兑现未使用
                Coupon updatedCoupon = couponRepository.save(coupon);
                return new ResponseData<Coupon>().success(updatedCoupon);
            } else {
                return new ResponseData<Coupon>().fail(ResponseEnum.COUPON_NOT_AVAILABLE);
            }
        }
        return new ResponseData<Coupon>().fail(ResponseEnum.COUPON_NOT_FOUND);
    }

    @Override
    public ResponseData<Void> deleteCoupon(Integer couponId) {
        couponRepository.deleteById(couponId);
        return new ResponseData<Void>().success();
    }

    @Override
    public ResponseData<Coupon> updateCouponStatus(Integer couponId, String status) {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        if (optionalCoupon.isPresent()) {
            Coupon coupon = optionalCoupon.get();
            coupon.setStatus(status);
            Coupon updatedCoupon = couponRepository.save(coupon);
            return new ResponseData<Coupon>().success(updatedCoupon);
        }
        return new ResponseData<Coupon>().fail(ResponseEnum.COUPON_NOT_FOUND);
    }

    @Override
    public ResponseData<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        return new ResponseData<List<Coupon>>().success(coupons);
    }

    @Override
    public ResponseData<Coupon> getCouponBySerialNumber(BigDecimal serialNumber) {
        Optional<Coupon> coupon = couponRepository.findBySerialNumber(serialNumber);
        if (coupon.isPresent()) {
            return new ResponseData<Coupon>().success(coupon.get());
        }
        return new ResponseData<Coupon>().fail(ResponseEnum.COUPON_NOT_FOUND);
    }
}

