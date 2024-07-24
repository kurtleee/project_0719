package com.uniview.project0719.entity;

import jakarta.persistence.*;

/**
 * @author ：zx
 * @date ：Created in 2024/7/24 14:51
 * @description：
 * @modified By：
 * @version: $
 */
@Entity
@Table(name = "t_position")
public class Position {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "position_name")
    private String positionName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

}
