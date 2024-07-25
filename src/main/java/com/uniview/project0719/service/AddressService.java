package com.uniview.project0719.service;

import com.uniview.project0719.entity.Address;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

public interface AddressService {
    /**
     * 添加地址
     */
    ResponseData<?> addAddress(Address address);

    /**
     * 根据id查询地址
     */
    ResponseData<?> findUserAddress(ParamData<Address> paramData) throws ParseException;
    /**
     * 删除收货地址
     */
    ResponseData<?> deleteAddressById(Integer id);
}
