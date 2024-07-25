package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Integer> {
    Page<Good> findAllByClassificationId(Integer classificationId,Pageable pageable);

    //管理端商品查询列表
    Page<Good> findAll(Specification<Good> spec, Pageable pageable);

    /**
     * 通过id查询数据库中商品，用在添加购物车接口中
     * @param id
     * @return
     */
    Good findGoodById(Integer id);
}
