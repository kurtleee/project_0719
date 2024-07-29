package com.uniview.project0719.controller;

import com.uniview.project0719.dto.DeliveryDTO;
import com.uniview.project0719.dto.SortDTO;
import com.uniview.project0719.service.DeliveryService;
import com.uniview.project0719.service.SortService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.controller
 * @Project：project_0719
 * @name：SortController
 * @Date：2024/7/26 12:16
 * @Filename：SortController
 */
@RestController
@RequestMapping("/delivery")
@Tag(name = "SortController", description = "分拣监控控制器")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/getDeliveryList")
    @Operation(summary="获取配送列表")
    public ResponseData<?> getSortList(@RequestBody ParamData<DeliveryDTO> paramData) {
        return deliveryService.findAllDeliveries(paramData);
    }
    @PostMapping("/getDeliverymanDeliveryList")
    @Operation(summary = "获取配送员端的配送列表，即接单中心页面",description = "只需传入分页参数")
    public ResponseData<?> getDeliverymanDeliveryList(@RequestBody ParamData<DeliveryDTO> paramData){
        try {
            return deliveryService.findDeliveriesByRepository(paramData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/getCurrentDeliverymanList")
    @Operation(summary = "我的任务界面")
    public ResponseData<?> getCurrentDeliverymanList(@RequestBody ParamData<DeliveryDTO> paramData){
        try {
            return deliveryService.findDeliveryByStatus(paramData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
