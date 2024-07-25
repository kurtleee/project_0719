package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodRepository extends JpaRepository<Good, Integer>, JpaSpecificationExecutor<Good> {
    /**
     * 通过id查询数据库中商品，用在添加购物车接口中
     * @param id
     * @return
     */
    Good findGoodById(Integer id);
}
