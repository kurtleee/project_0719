package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Sorter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 9:23
 * @description：
 * @modified By：
 * @version: $
 */
public interface SorterRepository extends JpaRepository<Sorter,Integer>, JpaSpecificationExecutor<Sorter> {

}
