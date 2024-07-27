package com.uniview.project0719.controller;


import com.uniview.project0719.entity.Coupon;
import com.uniview.project0719.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponService.createCoupon(coupon);
    }

    @PostMapping("/redeem/{couponId}")
    @Operation(summary = "兑换优惠券")
    public Coupon redeemCoupon(@PathVariable Integer couponId, @RequestParam Integer userId) {
        return couponService.redeemCoupon(couponId, userId);
    }

    @DeleteMapping("/delete/{couponId}")
    @Operation(summary = "删除优惠券")
    public void deleteCoupon(@PathVariable Integer couponId) {
        couponService.deleteCoupon(couponId);
    }

    @PutMapping("/status/{couponId}")
    @Operation(summary = "更新优惠券状态")
    public Coupon updateCouponStatus(@PathVariable Integer couponId, @RequestParam String status) {
        return couponService.updateCouponStatus(couponId, status);
    }

    @GetMapping
    @Operation(summary = "获取所有优惠券")
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/getCoupon/{couponId}")
    @Operation(summary = "获取指定优惠券")
    public Coupon getCouponById(@PathVariable Integer couponId) {
        return couponService.getCouponById(couponId);
    }
}

