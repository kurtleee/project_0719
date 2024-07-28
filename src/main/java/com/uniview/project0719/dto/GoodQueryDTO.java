package com.uniview.project0719.dto;

import com.uniview.project0719.entity.Classification;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodQueryDTO {
    /**
     * 商品查询DTO
     */
    private Integer id;
    private String title;
    private Integer status;
    private Classification classification;
    private Double minCurrentPrice;
    private Double maxCurrentPrice;
    private Double minOriginalPrice;
    private Double maxOriginalPrice;
    private Type type;

}
