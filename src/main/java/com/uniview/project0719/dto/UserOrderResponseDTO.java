package com.uniview.project0719.dto;

import com.uniview.project0719.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderResponseDTO {
    private String orderId;
    private Instant orderDate;
    private BigDecimal orderPrice;
    private Integer buyCount;
    private String detailAddress;
    private Integer status;
}
