package com.uniview.project0719.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "t_user_order")
public class UserOrder {
    @Id
    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "order_price", precision = 10, scale = 2)
    private BigDecimal orderPrice;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_date")
    private Instant orderDate;

}