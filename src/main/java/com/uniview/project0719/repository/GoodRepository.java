package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Integer> {
    Page<Good> findAllByClassificationId(Integer classificationId,Pageable pageable);
}
