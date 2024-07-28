package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Community;
import com.uniview.project0719.entity.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 9:23
 * @description：
 * @modified By：
 * @version: $
 */
public interface CommunityRepository extends JpaRepository<Community, Integer>, JpaSpecificationExecutor<Community> {
    Page<Community> findCommunitiesByRegionAndRepositoryIsNull(String region, Pageable pageable);

    Community findCommunityById(Integer id);

    List<Community> findCommunitiesByRepository(Repository repository);
}
