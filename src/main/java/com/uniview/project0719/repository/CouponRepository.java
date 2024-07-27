package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kurt LEE
 * @created 2024/07/27 - 上午11:10
 * 优惠券的数据访问层
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
}
