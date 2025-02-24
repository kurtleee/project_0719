package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.RepositoryDTO;
import com.uniview.project0719.dto.RepositoryResponseDTO;
import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.repository.RepositoryRepository;
import com.uniview.project0719.service.RepositoryService;
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
 * @date ：Created in 2024/7/25 17:44
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {
    @Autowired
    private RepositoryRepository repositoryRepository;

    @Override
    public ResponseData<?> findRepositories(ParamData<RepositoryDTO> paramData) {
        Specification<Repository> spec = Specification.where(Specifications.repositoryHasCity(paramData.getParam().getCity()))
                .and(Specifications.repositoryHasRegion(paramData.getParam().getRegion()))
                .and(Specifications.repositoryHasAreaBetween(paramData.getParam().getMinArea(), paramData.getParam().getMaxArea()))
                .and(Specifications.repositoryHasCommunityBetween(paramData.getParam().getMinCommunity(), paramData.getParam().getMaxCommunity()))
                .and(Specifications.repositoryHasSorterBetween(paramData.getParam().getMinSorter(), paramData.getParam().getMaxSorter()))
                .and(Specifications.repositoryHasDeliverymanBetween(paramData.getParam().getMinDeliveryman(), paramData.getParam().getMaxDeliveryman()));
        if (!"".equals(paramData.getParam().getNameOrAddress()) && paramData.getParam().getNameOrAddress() != null) {
            spec = spec.and((Specifications.repositoryHasNameLike(paramData.getParam().getNameOrAddress()))
                    .or(Specifications.repositoryHasAddressLike(paramData.getParam().getNameOrAddress())));
        }
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Repository> repositoryPage = repositoryRepository.findAll(spec, pageable);
        List<RepositoryResponseDTO> resultList = new ArrayList<>();
        repositoryPage.getContent().forEach(e -> {
            RepositoryResponseDTO repositoryResponseDTO = new RepositoryResponseDTO();
            BeanUtils.copyProperties(e, repositoryResponseDTO);
            repositoryResponseDTO.setCommunityCount(e.getCommunities().size());
            repositoryResponseDTO.setSorterCount(e.getSorters().size());
            repositoryResponseDTO.setDeliverymanCount(e.getDeliverymen().size());
            resultList.add(repositoryResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", repositoryPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findRepositoryDetail(Integer id) {
        return new ResponseData<>().success(repositoryRepository.findRepositoryById(id));
    }

    @Override
    public ResponseData<?> updateRepository(Repository repository) {
        return new ResponseData<>().success(repositoryRepository.save(repository));
    }
}
