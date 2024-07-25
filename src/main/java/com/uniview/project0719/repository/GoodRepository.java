package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Good;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GoodRepository extends JpaRepository<Good, Integer> {
    Page<Good> findAllByClassificationId(Integer classificationId,Pageable pageable);

    //管理端商品查询列表
    Page<Good> findAll(Specification<Good> spec, Pageable pageable);

    //管理端根据id修改商品状态
    @Modifying  //注解用于标识一个更新、删除或插入操作
    @Transactional
    @Query("UPDATE Good g SET g.status = ?1 WHERE g.id = ?2")
    /**
     * UPDATE Good g: 表示要更新 Good 实体对应的数据库表中的记录，g 是 Good 实体的别名。
     * SET g.status = ?1: 表示要将 Good 实体的 status 字段更新为第一个参数的值（?1 表示方法中的第一个参数）。
     * WHERE g.id = ?2: 表示只更新 id 字段等于第二个参数的记录（?2 表示方法中的第二个参数）
     **/
    int updateStatusById(Integer status, Integer id);

    Good findGoodById(Integer id);
}
