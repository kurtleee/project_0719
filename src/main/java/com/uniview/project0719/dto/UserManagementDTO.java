package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementDTO {
    private Integer userId;
    private String wxId;
    private String nickName;
    private Integer status;
    private Instant createTime;
    private Integer minTotalOrders;
    private Integer maxTotalOrders;
    private BigDecimal minOrderPrice;
    private BigDecimal maxOrderPrice;
    private Integer totalOrders;
    private BigDecimal totalOrderPrice;
}
