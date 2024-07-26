package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.SorterDTO;
import com.uniview.project0719.dto.SorterResponseDTO;
import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.entity.Sorter;
import com.uniview.project0719.repository.RepositoryRepository;
import com.uniview.project0719.repository.SorterRepository;
import com.uniview.project0719.service.SorterService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ResponseData<?> findSorter(ParamData<SorterDTO> paramData) {
        Repository repository = repositoryRepository.findRepositoryById(paramData.getParam().getRepositoryId());
        Specification<Sorter> spec = Specification.where(Specifications.sorterHasRepository(repository))
                .and(Specifications.sorterHasNameLike(paramData.getParam().getNameOrPhone()))
                .or(Specifications.sorterHasPhoneLike(paramData.getParam().getNameOrPhone()));
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
        map.put("total", sorterPage.getTotalPages());
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
}
