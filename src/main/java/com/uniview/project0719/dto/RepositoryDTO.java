package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/25 18:50
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryDTO {
    private String nameOrAddress;
    private String city;
    private String region;
    private Integer minArea;
    private Integer maxArea;
    private Integer minCommunity;
    private Integer maxCommunity;
    private Integer minSorter;
    private Integer maxSorter;
    private Integer minDeliveryman;
    private Integer maxDeliveryman;
}
