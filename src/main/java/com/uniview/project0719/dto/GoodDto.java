package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDto {
    private Integer id;
    private String title;
    private BigDecimal currentPrice;
    private BigDecimal originalPrice;
    private String saleCount;
    private String imgSrc;

}
