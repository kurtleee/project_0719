package com.uniview.project0719.controller;

import com.uniview.project0719.dto.AddressDTO;
import com.uniview.project0719.entity.Address;
import com.uniview.project0719.service.AddressService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/address")
@Tag(name = "地址接口", description = "Address Controller")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 添加地址
     */
    @PostMapping("/addAddress")
    public ResponseData<?> addAddress(@RequestBody AddressDTO addressDTO){
        try {
            return addressService.addAddress(addressDTO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
}
    /**
     * 根据用户id查询
     */

    @PostMapping("/getAddressList")
    public ResponseData<?> findAddressList(@RequestBody ParamData<Address> paramData){
        try {
            return addressService.findUserAddress(paramData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id删除地址
     */
    @DeleteMapping("/deleteAddress/deleteAddress")
    public ResponseData<?> deleteAddress(@RequestParam Integer id){
        return addressService.deleteAddressById(id);
    }
}
