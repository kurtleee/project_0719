package com.uniview.project0719.entity;

import jakarta.persistence.*;

/**
 * @author ：zx
 * @date ：Created in 2024/7/24 14:44
 * @description：
 * @modified By：
 * @version: $
 */
@Entity
@Table(name = "t_authority")
public class Authority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "auth_name")
    private String authName;

    @Column(name = "auth_code")
    private String authCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
