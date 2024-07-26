package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 14:05
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverymanResponseDTO {
    private Integer id;
    private Integer repositoryId;
    private String name;
    private String phone;
    private Integer status;
}
