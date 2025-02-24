package com.uniview.project0719.service;

import com.uniview.project0719.dto.DeliverymanDTO;
import com.uniview.project0719.entity.Deliveryman;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 14:01
 * @description：
 * @modified By：
 * @version: $
 */
public interface DeliverymanService {
    /**
     * 通过手机号、姓名以及所属仓库查询配送员
     * @param paramData
     * @return
     */
    ResponseData<?> findDeliverymen(ParamData<DeliverymanDTO> paramData);

    /**
     * 查询无所属仓库的配送员
     * @param paramData
     * @return
     */
    ResponseData<?> findDeliverymenAvailable(ParamData<DeliverymanDTO> paramData);

    /**
     * 用于修改配送员状态、或将配送员移出/入本仓库
     * 前端将本行数据作为对象传入后端，修改状态将status属性改变，移出仓库将repositoryId设置为null
     * @param deliverymanDTO
     * @return
     */
    ResponseData<?> updateDeliveryman(DeliverymanDTO deliverymanDTO);

    /**
     * 配送员注册，除了账号密码外前端还需要传来一个密钥，防止非本公司员工注册
     * 密钥存在redis中，定期更改，执行添加之前先判断前端密钥是否与redis中一致，一致则注册成功，否则不允许注册
     * @param deliverymanDTO
     * @return
     */
    ResponseData<?> createDeliveryman(DeliverymanDTO deliverymanDTO);
}
