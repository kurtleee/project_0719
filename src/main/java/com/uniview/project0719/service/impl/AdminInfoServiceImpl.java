package com.uniview.project0719.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.uniview.project0719.dto.AdminDTO;
import com.uniview.project0719.entity.AdministratorInfo;
import com.uniview.project0719.repository.AdminInfoRepository;
import com.uniview.project0719.repository.PositionRepository;
import com.uniview.project0719.service.AdminInfoService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zx
 * @date ：Created in 2024/7/28 11:57
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    private AdminInfoRepository adminInfoRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Value("${login.salt}")
    String loginSalt;

    @Override
    public ResponseData<?> createAdmin(AdminDTO adminDTO) {
        AdministratorInfo administratorInfo = new AdministratorInfo();
        BeanUtils.copyProperties(adminDTO, administratorInfo, "id");
        administratorInfo.setPosition(positionRepository.findPositionById(adminDTO.getPositionId()));
        administratorInfo.setCreateTime(new Date().toInstant());
        administratorInfo.setStatus(1);
        administratorInfo.setPassword(SecureUtil.md5(adminDTO.getPassword() + loginSalt));
        adminInfoRepository.save(administratorInfo);
        return new ResponseData<>().success();
    }

    @Override
    public ResponseData<?> findAllAdmins(ParamData<AdminDTO> paramData) {
        Specification<AdministratorInfo> spec = Specification.where(Specifications.adminInfoHasStatus(paramData.getParam().getStatus()))
                .and(Specifications.adminInfoHasAccountLike(paramData.getParam().getAccount()));
        Pageable pageable = PageRequest.of(paramData.getPage(), paramData.getSize());
        Page<AdministratorInfo> administratorInfoPage = adminInfoRepository.findAll(spec, pageable);
        Map map = new HashMap<>();
        map.put("resultList",administratorInfoPage.getContent());
        map.put("total",administratorInfoPage.getTotalElements());
        return new ResponseData<>().success(map);
    }
}
