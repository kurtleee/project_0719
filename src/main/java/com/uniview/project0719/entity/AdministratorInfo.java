package com.uniview.project0719.entity;

import jakarta.persistence.*;

import java.time.Instant;

/**
 * @author ：zx
 * @date ：Created in 2024/7/24 14:38
 * @description：
 * @modified By：
 * @version: $
 */
@Entity
@Table(name = "t_administrator_info")
public class AdministratorInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "position_id")
    private Integer positionId;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "status")
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
