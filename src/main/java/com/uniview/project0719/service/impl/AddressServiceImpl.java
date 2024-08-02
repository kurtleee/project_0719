package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.AddressDTO;
import com.uniview.project0719.entity.Address;
import com.uniview.project0719.entity.Community;
import com.uniview.project0719.repository.AddressRepository;
import com.uniview.project0719.repository.CommunityRepository;
import com.uniview.project0719.service.AddressService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CommunityRepository communityRepository;

    /**
     * 添加地址
     */
    @Override
    public ResponseData<?> addAddress(AddressDTO addressDTO) throws ParseException {
        if (addressDTO.getRegion() == null || addressDTO.getReceiver() == null || addressDTO.getPhone() == null || addressDTO.getDetailAddress() == null) {
            return new ResponseData<>().fail(ResponseEnum.FAIL);
        }
        Address address = new Address();
        Integer userId = UserContext.getUserId();
        BeanUtils.copyProperties(addressDTO, address);
        String detailAddress = addressDTO.getCity() + addressDTO.getRegion() + addressDTO.getDetailAddress();
        Community community = communityRepository.findCommunityById(addressDTO.getCommunityId());
        address.setDetailAddress(detailAddress);
        address.setUserId(userId);
        address.setCommunity(community);
        address.setStatus(1);
        if (addressDTO.getDefaultAddress() == null) {
            address.setDefaultAddress(0);
        } else {
            Address originalDefaultAddr = addressRepository.findAddressByUserIdAndDefaultAddress(userId, 1);
            if (originalDefaultAddr != null) {
                originalDefaultAddr.setDefaultAddress(0);
                addressRepository.save(originalDefaultAddr);
            }
            address.setDefaultAddress(1);
        }
        addressRepository.save(address);
        return new ResponseData<>().success();
    }

    /**
     * 根据id查询地址
     */
    @Override
    public ResponseData<?> findUserAddress(ParamData<Address> paramData) throws ParseException {
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Address> addresses = addressRepository.findAddressesByUserIdAndStatus(UserContext.getUserId(), 1, pageable);
        Map map = new HashMap<>();
        map.put("resultList", addresses.getContent());
        map.put("total", addresses.getTotalElements());
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
