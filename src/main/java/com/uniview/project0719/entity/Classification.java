package com.uniview.project0719.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_classification")
public class Classification {
    @Id
    @Column(name = "classification_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classification_name")
    private String classificationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

}