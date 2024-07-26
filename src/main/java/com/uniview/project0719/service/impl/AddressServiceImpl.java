package com.uniview.project0719.service.impl;

import com.uniview.project0719.entity.Address;
import com.uniview.project0719.repository.AddressRepository;
import com.uniview.project0719.service.AddressService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseData<?> findUserAddress(ParamData<Address> paramData) throws ParseException {
        Pageable pageable = PageRequest.of(paramData.getPage(), paramData.getSize());
        Page<Address> addresses = addressRepository.findAddressesByUserIdAndStatus(UserContext.getUserId(), 1, pageable);
        Map map = new HashMap<>();
        map.put("resultList",addresses.getContent());
        map.put("total",addresses.getTotalPages());
        return new ResponseData<>().success(map);
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
