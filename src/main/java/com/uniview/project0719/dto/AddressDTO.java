package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/27 14:44
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Integer id;
    private String city;
    private String region;
    private String detailAddress;
    private String phone;
    private String receiver;
    private Integer userId;
    private Integer status;
    private Integer defaultAddress;
    private Integer communityId;
}
