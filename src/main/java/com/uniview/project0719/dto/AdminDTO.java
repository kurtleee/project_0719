package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author ：zx
 * @date ：Created in 2024/7/28 11:55
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private Integer id;
    private String account;
    private String password;
    private Integer positionId;
    private Instant createTime;
    private Integer status;
    private String phone;
}
