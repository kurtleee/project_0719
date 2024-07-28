package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodUpdateStatusDTO {
    /**
     * 商品状态批量更新DTO
     */
    private List<Integer> goodIds;
    private Integer status;
}
