package com.uniview.project0719.repository;

import com.uniview.project0719.entity.AdministratorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/28 11:42
 * @description：
 * @modified By：
 * @version: $
 */
public interface AdminInfoRepository extends JpaRepository<AdministratorInfo, Integer>, JpaSpecificationExecutor<AdministratorInfo> {

}
