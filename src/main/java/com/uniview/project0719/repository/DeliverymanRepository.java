package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Deliveryman;
import com.uniview.project0719.entity.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 9:23
 * @description：
 * @modified By：
 * @version: $
 */
public interface DeliverymanRepository extends JpaRepository<Deliveryman,Integer>, JpaSpecificationExecutor<Deliveryman> {
    /**
     * 按仓库搜索配送员
     * @param repository
     * @return
     */
    Page<Deliveryman> findDeliverymenByRepository(Repository repository, Pageable pageable);
}
