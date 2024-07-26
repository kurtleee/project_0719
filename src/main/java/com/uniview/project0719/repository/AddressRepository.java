package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    /**
     * 通过用户id查询地址
     *
     * @param userId
     * @return
     */
    Page<Address> findAddressesByUserIdAndStatus(Integer userId, Integer status, Pageable pageable);
}
