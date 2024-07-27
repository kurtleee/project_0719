package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.dto
 * @Project：project_0719
 * @name：SortDTO
 * @Date：2024/7/26 14:22
 * @Filename：SortDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    private String orderNumOrDeliverymanNameOrRepositoryId;
    private Integer status;
    private String city;
    private String region;
    private Instant minOrderTime;
    private Instant maxOrderTime;
    private Integer minGoodsNum;
    private Integer maxGoodsNum;
}
