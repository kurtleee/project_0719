package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.UserManagementDTO;
import com.uniview.project0719.entity.Good;
import com.uniview.project0719.entity.UserInfo;
import com.uniview.project0719.entity.UserOrder;
import com.uniview.project0719.repository.UserInfoRepository;
import com.uniview.project0719.repository.UserOrderRepository;
import com.uniview.project0719.service.UserManagementService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserOrderRepository userOrderRepository;

    /**
     * 获取用户列表
     * 此方法查询了用户的id、微信号、用户昵称、总订单数、总订单金额
     *
     * @param paramData
     * @return
     */
    @Override
    public ResponseData<?> getUserList(ParamData<UserManagementDTO> paramData) {
        UserManagementDTO param = paramData.getParam();

        // 检查 param 是否为 null，如果是，则初始化一个新的 UserManagementDTO 对象
        if (param == null) {
            param = new UserManagementDTO();
        }

        // 构建 Specification 查询条件
        Specification<UserInfo> spec = Specification.where(null);

        if (param.getUserId() != null) { // 判断是否有用户id
            spec = spec.and(Specifications.userInfoHasUserIdLike(param.getUserId()));
        }
        if (param.getWxId() != null && !param.getWxId().isEmpty()) { // 判断是否有微信号
            spec = spec.and(Specifications.userInfoHasWxIdLike(param.getWxId()));
        }
        if (param.getNickName() != null && !param.getNickName().isEmpty()) { // 判断是否有用户昵称
            spec = spec.and(Specifications.userInfoHasNickNameLike(param.getNickName()));
        }

        // 检查 paramData.getPage() 和 paramData.getSize() 是否为 null
        Integer page = paramData.getPage();
        Integer size = paramData.getSize();
        if (page == null || size == null) {
            throw new IllegalArgumentException("Page number and size must not be null");
        }

        // 使用 Pageable 进行分页查询
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<UserInfo> userPage = userInfoRepository.findAll(spec, pageable);

        // 获取分页数据
        List<UserInfo> userList = userPage.getContent(); // 修改：从 userPage 获取分页数据

        // 获取所有用户的订单信息，避免在循环中查询数据库
        List<UserOrder> allUserOrders = userOrderRepository.findAll((root, query, cb) -> cb.equal(root.get("status"), 3));
        System.out.println("All User Orders: " + allUserOrders);


        // 使用 Map 将用户ID与其订单关联起来
        Map<Integer, List<UserOrder>> userOrdersMap = allUserOrders.stream()
                .collect(Collectors.groupingBy(UserOrder::getUserId));  //使用 UserOrder 对象的 getUserId 方法返回的值（即用户的 ID）作为分组键，将订单分组
        System.out.println("User Orders Map: " + userOrdersMap);

        // 过滤出有订单的用户
        List<UserInfo> filteredUserList = userList.stream()
                .filter(user -> userOrdersMap.containsKey(user.getId()))
                .collect(Collectors.toList());
        System.out.println("Filtered User List: " + filteredUserList);

        List<UserManagementDTO> userManagementDTOList = filteredUserList.stream().map(user -> {
            List<UserOrder> userOrders = userOrdersMap.getOrDefault(user.getId(), Collections.emptyList());
            System.out.println("Processing user: " + user.getId() + ", Orders: " + userOrders);

            int totalOrders = userOrders.size();
            BigDecimal totalOrderPrice = userOrders.stream()
                    .map(UserOrder::getOrderPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            UserManagementDTO userManagementDTO = new UserManagementDTO();
            userManagementDTO.setUserId(user.getId());
            userManagementDTO.setWxId(user.getWxId());
            userManagementDTO.setNickName(user.getNickName());
            userManagementDTO.setCreateTime(user.getCreateTime());
            userManagementDTO.setTotalOrders(totalOrders);
            userManagementDTO.setTotalOrderPrice(totalOrderPrice);

            System.out.println("Created UserManagementDTO: " + userManagementDTO);

            return userManagementDTO;
        }).collect(Collectors.toList());
        System.out.println("User Management DTO List: " + userManagementDTOList);

        // 根据 minTotalOrders, maxTotalOrders, minOrderPrice, maxOrderPrice 过滤用户
        UserManagementDTO finalParam = param;
        userManagementDTOList = userManagementDTOList.stream()
                .filter(userManagementDTO -> {
                    boolean matches = true;
                    if (finalParam.getMinTotalOrders() != null) {
                        matches = matches && userManagementDTO.getTotalOrders() >= finalParam.getMinTotalOrders();
                    }
                    if (finalParam.getMaxTotalOrders() != null) {
                        matches = matches && userManagementDTO.getTotalOrders() <= finalParam.getMaxTotalOrders();
                    }
                    if (finalParam.getMinOrderPrice() != null) {
                        matches = matches && userManagementDTO.getTotalOrderPrice().compareTo(finalParam.getMinOrderPrice()) >= 0;
                    }
                    if (finalParam.getMaxOrderPrice() != null) {
                        matches = matches && userManagementDTO.getTotalOrderPrice().compareTo(finalParam.getMaxOrderPrice()) <= 0;
                    }
                    return matches;
                })
                .collect(Collectors.toList());

        // 创建分页结果
        Page<UserManagementDTO> pageResult = new PageImpl<>(userManagementDTOList, pageable, userManagementDTOList.size());

        // 返回结果
        Map<String, Object> map = new HashMap<>();
        map.put("resultList", pageResult.getContent());
        map.put("total", pageResult.getTotalElements());

        return new ResponseData<>().success(map);
    }
}
