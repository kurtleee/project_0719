package com.uniview.project0719.repository;

import com.uniview.project0719.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    /**
     * 通过用户id查询地址
     * @param userId
     * @return
     */
    List<Address> findAddressesByUserIdAndStatus(Integer userId,Integer status);
}
