package com.uniview.project0719.entity;

import jakarta.persistence.*;

/**
 * @author ：zx
 * @date ：Created in 2024/7/29 12:13
 * @description：
 * @modified By：
 * @version: $
 */
@Entity
@Table(name = "t_admin_auth")
public class AdminAuth {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "admin_id")
    private Integer adminId;
    @Column(name = "auth_id")
    private Integer authId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminAuth that = (AdminAuth) o;

        if (id != that.id) return false;
        if (adminId != null ? !adminId.equals(that.adminId) : that.adminId != null) return false;
        if (authId != null ? !authId.equals(that.authId) : that.authId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (adminId != null ? adminId.hashCode() : 0);
        result = 31 * result + (authId != null ? authId.hashCode() : 0);
        return result;
    }
}
