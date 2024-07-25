package com.uniview.project0719.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "t_sort")
public class Sort {
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
    @JoinColumn(name = "sorter_id")
    private Sorter sorter;

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

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}