package com.uniview.project0719.dto;

import com.uniview.project0719.entity.OrderItem;
import com.uniview.project0719.entity.UserOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.dto
 * @Project：project_0719
 * @name：SortResponseDTO
 * @Date：2024/7/27 10:03
 * @Filename：SortResponseDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortResponseDTO {
    private String orderNum;
    private Instant orderTime;
    private Integer goodsNum;
    private String sortTime;
    private Integer status;
    private String sorterName;
    private String city;
    private String region;
    private String address;
    private String phone;
    private String receiver;
    private String repositoryName;
    private List<OrderItemResponseDTO> orderItems;
}
