package com.uniview.project0719.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderParamDTO {
    private Integer addressId;
    private List<Integer> ids;
}
