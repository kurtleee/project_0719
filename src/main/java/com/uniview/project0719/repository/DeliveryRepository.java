package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Delivery;
import com.uniview.project0719.entity.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.repository
 * @Project：project_0719
 * @name：SortRepository
 * @Date：2024/7/26 14:51
 * @Filename：SortRepository
 */
public interface DeliveryRepository extends JpaRepository<Delivery,Integer>, JpaSpecificationExecutor<Delivery> {
}
