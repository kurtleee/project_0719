package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.Address;
import com.uniview.project0719.repository.AddressRepository;
import com.uniview.project0719.service.AddressService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    /**
     * 添加地址
     */
    @Override
    public ResponseData<?> addAddress(Address address) {
        addressRepository.save(address);
        return new ResponseData<>().success();
    }

    /**
     * 根据id查询地址
     */
    @Override
    public ResponseData<?> findAddressById(Integer userId) {
        Optional<Address> byUserId = addressRepository.findById(userId);
        return new ResponseData<>().success(byUserId);
    }

    /**
     * 根据id删除地址
     */
    @Override
    public ResponseData<?> deleteAddressById(Integer id) {
        addressRepository.deleteById(id);
        return new ResponseData<>().success();
    }
}
