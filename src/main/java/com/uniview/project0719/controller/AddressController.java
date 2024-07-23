package com.uniview.project0719.controller;

import com.uniview.project0719.entity.Address;
import com.uniview.project0719.service.AddressService;
import com.uniview.project0719.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 添加地址
     */
    @PostMapping("/addAddress")
    public ResponseData<?> addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }

    /**
     * 根据用户id查询
     */

    @GetMapping("/getAddressList")
    public ResponseData<?> findAddressListById(@RequestParam Integer userId){
        return addressService.findAddressById(userId);
    }

    /**
     * 根据id删除地址
     */
    @DeleteMapping("/deleteAddress/deleteAddress")
    public ResponseData<?> deleteAddress(@RequestParam Integer id){
        return addressService.deleteAddressById(id);
    }
}
