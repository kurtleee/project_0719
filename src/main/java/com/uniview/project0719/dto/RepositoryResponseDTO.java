package com.uniview.project0719.dto;

import com.uniview.project0719.entity.Repository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 10:36
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryResponseDTO {
    private String name;
    private String city;
    private String region;
    private String address;
    private Integer area;
    private Integer communityCount;
    private Integer deliverymanCount;
    private Integer sorterCount;
}
