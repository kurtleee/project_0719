package com.uniview.project0719.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "t_delivery")
public class Delivery {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "submit_time")
    private Instant submitTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "deliveryman_id")
    private Integer deliverymanId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Instant getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Instant submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(Integer deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

}