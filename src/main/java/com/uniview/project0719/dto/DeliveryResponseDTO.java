package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.dto
 * @Project：project_0719
 * @name：SortResponseDTO
 * @Date：2024/7/27 10:03
 * @Filename：SortResponseDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponseDTO {
    private String orderNum;
    private Instant orderTime;
    private Integer goodsNum;
    private String deliveryTime;
    private Integer status;
    private String deliverymanName;
    private String city;
    private String region;
    private String address;
    private String repositoryName;

}
