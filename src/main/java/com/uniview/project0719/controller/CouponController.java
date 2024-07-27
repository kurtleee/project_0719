package com.uniview.project0719.controller;


import com.uniview.project0719.entity.Coupon;
import com.uniview.project0719.service.CouponService;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kurt LEE
 * @created 2024/07/27 - 上午11:15
 * 优惠券的控制器
 */
@RestController
@RequestMapping("/coupons")
@Tag(name = "优惠券接口", description = "优惠券控制器")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping
    @Operation(summary = "新建优惠券", description = "优惠券兑换码随机生成")
    public ResponseData<Coupon> createCoupon(@RequestBody Coupon coupon) {
        return couponService.createCoupon(coupon);
    }


    @PostMapping("/redeem/{serialNumber}")
    @Operation(summary = "兑换优惠券", description = "需要根据serialNumber和userId兑换")
    public ResponseData<Coupon> redeemCoupon(@RequestParam BigDecimal serialNumber, @RequestParam Integer userId) {
        return couponService.redeemCoupon(serialNumber, userId);
    }

    @DeleteMapping("/delete/{couponId}")
    @Operation(summary = "删除优惠券")
    public void deleteCoupon(@PathVariable Integer couponId) {
        couponService.deleteCoupon(couponId);
    }

    @PutMapping("/status/{couponId}")
    @Operation(summary = "使用内部ID更新优惠券状态", description = "status为1表示未兑现，2表示已兑现未使用，3表示已使用, 4表示已过期")
    public ResponseData<Coupon> updateCouponStatus(@PathVariable Integer couponId, @RequestParam String status) {
        return couponService.updateCouponStatus(couponId, status);
    }

    @GetMapping
    @Operation(summary = "获取所有优惠券")
    public ResponseData<List<Coupon>> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/getCoupon/{serialNumber}")
    @Operation(summary = "获取指定优惠券")
    public ResponseData<Coupon> getCouponBySerialNumber(@PathVariable BigDecimal serialNumber) {
        return couponService.getCouponBySerialNumber(serialNumber);
    }
}

