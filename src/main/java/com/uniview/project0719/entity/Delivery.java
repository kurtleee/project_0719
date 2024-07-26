package com.uniview.project0719.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "t_delivery")
public class Delivery {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private UserOrder userOrder;

    @Column(name = "submit_time")
    private Instant submitTime;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "deliveryman_id")
    private Deliveryman deliveryman;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Deliveryman getDeliveryman() {
        return deliveryman;
    }

    public void setDeliveryman(Deliveryman deliveryman) {
        this.deliveryman = deliveryman;
    }
}