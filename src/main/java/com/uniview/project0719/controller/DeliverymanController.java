package com.uniview.project0719.controller;

import com.uniview.project0719.dto.DeliverymanDTO;
import com.uniview.project0719.service.DeliverymanService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 14:42
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/deliveryman")
@Tag(name = "配送员接口")
public class DeliverymanController {
    @Autowired
    private DeliverymanService deliverymanService;

    @PostMapping("/getDeliverymanList")
    @Operation(summary = "获取当前仓库的配送员列表")
    public ResponseData<?> getDeliverymanList(@RequestBody ParamData<DeliverymanDTO> paramData) {
        return deliverymanService.findDeliverymen(paramData);
    }

    @PostMapping("/getAvailableDeliverymanList")
    @Operation(summary = "获取无所属仓库的配送员列表")
    public ResponseData<?> getAvailableDeliverymanList(@RequestBody ParamData<DeliverymanDTO> paramData) {
        return deliverymanService.findDeliverymenAvailable(paramData);
    }

    @PostMapping("/updateDeliveryman")
    @Operation(summary = "修改配送员状态或将配送员移出仓库", description = "将该行对象传至后端，修改状态则单独将status属性改变，移出仓库则将repositoryId设为null")
    public ResponseData<?> updateDeliveryman(@RequestBody DeliverymanDTO deliverymanDTO) {
        return deliverymanService.updateDeliveryman(deliverymanDTO);
    }

    @PostMapping("/createDeliveryman")
    @Operation(summary = "配送员注册", description = "需要传入公司密钥key")
    public ResponseData<?> createDeliveryman(@RequestBody DeliverymanDTO deliverymanDTO) {
        return deliverymanService.createDeliveryman(deliverymanDTO);
    }
}
