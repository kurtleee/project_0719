package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDTO {
    private String orderIdOrAddress;
    private Integer status;
    private Instant minOrderDate;
    private Instant maxOrderDate;
    private BigDecimal minOrderPrice;
    private BigDecimal maxOrderPrice;
    private Integer minBuyCount;
    private Integer maxBuyCount;
}
