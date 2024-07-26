package com.uniview.project0719.dto;

import com.uniview.project0719.entity.Address;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 11:10
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
    private Integer id;
    private String goodsName;
    private BigDecimal price;
    private Integer buyCount;
    private BigDecimal sumPrice;
    private Address address;
    private Good good;
    private Instant createTime;
    private String orderId;
}
