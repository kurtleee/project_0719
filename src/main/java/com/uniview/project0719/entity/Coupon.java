package com.uniview.project0719.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "t_coupon")
public class Coupon {
    @Id
    @Column(name = "coupon_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial_number", precision = 10, scale = 2)
    private BigDecimal serialNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "coupon_value", precision = 10, scale = 2)
    private BigDecimal couponValue;

    @Column(name = "vali_util")
    private Instant valiUtil;

    @Column(name = "type")
    private String type;

    @Column(name = "product_id")
    private Integer productId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(BigDecimal serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public Instant getValiUtil() {
        return valiUtil;
    }

    public void setValiUtil(Instant valiUtil) {
        this.valiUtil = valiUtil;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

}