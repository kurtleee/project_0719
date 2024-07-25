package com.uniview.project0719.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_repository")
public class Repository {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "address")
    private String address;

    @Column(name = "area")
    private Integer area;

    @OneToMany(mappedBy = "repository")
    @JsonIgnoreProperties(value = "repository")
    private List<Community> communities;

    @OneToMany(mappedBy = "repository")
    @JsonIgnoreProperties(value = "repository")
    private List<Sorter> sorters;

    @OneToMany(mappedBy = "repository")
    @JsonIgnoreProperties(value = "repository")
    private List<Deliveryman> deliverymen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public List<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(List<Community> communities) {
        this.communities = communities;
    }

    public List<Sorter> getSorters() {
        return sorters;
    }

    public void setSorters(List<Sorter> sorters) {
        this.sorters = sorters;
    }

    public List<Deliveryman> getDeliverymen() {
        return deliverymen;
    }

    public void setDeliverymen(List<Deliveryman> deliverymen) {
        this.deliverymen = deliverymen;
    }
}