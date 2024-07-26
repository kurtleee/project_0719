package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Integer> {
}
