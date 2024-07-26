package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：zx
 * @date ：Created in 2024/7/25 17:25
 * @description：
 * @modified By：
 * @version: $
 */
public interface RepositoryRepository extends JpaRepository<Repository,Integer>, JpaSpecificationExecutor<Repository> {
    Repository findRepositoryById(Integer id);
}
