package com.uniview.project0719.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderParamDto {
    private Integer addressId;
    private List<Integer> ids;
}
