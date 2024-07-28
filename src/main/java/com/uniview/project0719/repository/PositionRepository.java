package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：zx
 * @date ：Created in 2024/7/28 17:24
 * @description：
 * @modified By：
 * @version: $
 */
public interface PositionRepository extends JpaRepository<Position,Integer> {
    Position findPositionById(Integer id);
}
