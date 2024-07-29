package com.uniview.project0719.mapper;

import java.util.List;

/**
 * @author ：zx
 * @date ：Created in 2024/7/29 19:19
 * @description：
 * @modified By：
 * @version: $
 */
public interface AuthMapper {
    List<String> findAdminAuthById(Integer id);
}
