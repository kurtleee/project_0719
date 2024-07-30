package com.uniview.project0719.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.uniview.project0719.dto.SorterDTO;
import com.uniview.project0719.dto.SorterResponseDTO;
import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.entity.Sorter;
import com.uniview.project0719.repository.RepositoryRepository;
import com.uniview.project0719.repository.SorterRepository;
import com.uniview.project0719.service.SorterService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.ResponseEnum;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 14:03
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class SorterServiceImpl implements SorterService {
    @Autowired
    private SorterRepository sorterRepository;
    @Autowired
    private RepositoryRepository repositoryRepository;
    @Value("${login.salt}")
    String loginSalt;

    @Override
    public ResponseData<?> findSorter(ParamData<SorterDTO> paramData) {
        Repository repository = repositoryRepository.findRepositoryById(paramData.getParam().getRepositoryId());
        Specification<Sorter> spec = Specification.where(Specifications.sorterHasRepository(repository));
        if ((!"".equals(paramData.getParam().getNameOrPhone())) && paramData.getParam().getNameOrPhone() != null) {
            spec = spec.and((Specifications.sorterHasNameLike(paramData.getParam().getNameOrPhone())).or(Specifications.sorterHasPhoneLike(paramData.getParam().getNameOrPhone())));
        }
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Sorter> sorterPage = sorterRepository.findAll(spec, pageable);
        List<SorterResponseDTO> resultList = new ArrayList<>();
        sorterPage.getContent().forEach(e -> {
            SorterResponseDTO sorterResponseDTO = new SorterResponseDTO();
            BeanUtils.copyProperties(e, sorterResponseDTO);
            sorterResponseDTO.setRepositoryId(e.getRepository().getId());
            resultList.add(sorterResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", sorterPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findSorterAvailable(ParamData<SorterDTO> paramData) {
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Sorter> sorterPage = sorterRepository.findSorterByRepositoryIsNull(pageable);
        Map map = new HashMap<>();
        map.put("resultList", sorterPage.getContent());
        map.put("total", sorterPage.getTotalPages());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> updateSorter(SorterDTO sorterDTO) {
        Sorter sorter = sorterRepository.findSorterById(sorterDTO.getId());
        BeanUtils.copyProperties(sorterDTO, sorter);
        if (sorterDTO.getRepositoryId() == null) {
            sorter.setRepository(null);
        } else {
            Repository repository = repositoryRepository.findRepositoryById(sorterDTO.getRepositoryId());
            sorter.setRepository(repository);
        }
        sorterRepository.save(sorter);
        return new ResponseData<>().success();
    }

    @Override
    public ResponseData<?> createSorter(SorterDTO sorterDTO) {
        // 从redis中拿取公司密钥，下面用"密钥"代替
        if ("密钥".equals(sorterDTO.getKey())) {
            Sorter sorter = new Sorter();
            BeanUtils.copyProperties(sorterDTO, sorter);
            sorter.setRepository(repositoryRepository.findRepositoryById(sorterDTO.getRepositoryId()));
            String md5Pw = SecureUtil.md5(sorterDTO.getPassword() + loginSalt);
            sorter.setPassword(md5Pw);
            sorter.setStatus(1);
            sorterRepository.save(sorter);
            return new ResponseData<>().success();
        } else {
            return new ResponseData<>().fail(ResponseEnum.NO_AUTHORITY);
        }
    }
}
