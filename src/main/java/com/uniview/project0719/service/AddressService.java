package com.uniview.project0719.service;

import com.uniview.project0719.dto.AddressDTO;
import com.uniview.project0719.entity.Address;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

public interface AddressService {
    /**
     * 添加地址功能
     * 小区信息在数据库和前端写死，两者id也要对应上
     * 小区信息关联仓库id，只有顾客地址关联上仓库才能选择该仓库分拣员与骑手为顾客服务
     * @param addressDTO
     * @return
     * @throws ParseException
     */
    ResponseData<?> addAddress(AddressDTO addressDTO) throws ParseException;

    /**
     * 根据id查询地址
     */
    ResponseData<?> findUserAddress(ParamData<Address> paramData) throws ParseException;
    /**
     * 删除收货地址
     */
    ResponseData<?> deleteAddressById(Integer id);
}
