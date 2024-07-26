package com.uniview.project0719.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 16:26
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    private Integer id;
    private String name;
    private Integer repositoryId;
}
