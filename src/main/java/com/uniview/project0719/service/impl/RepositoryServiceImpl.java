package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.RepositoryDTO;
import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.repository.RepositoryRepository;
import com.uniview.project0719.service.RepositoryService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Specification<Repository> spec = Specification.where(Specifications.RepositoryHasNameLike(paramData.getParam().getNameOrAddress()))
                .or(Specifications.RepositoryHasAddressLike(paramData.getParam().getNameOrAddress()))
                .and(Specifications.RepositoryHasCity(paramData.getParam().getCity()))
                .and(Specifications.RepositoryHasRegion(paramData.getParam().getRegion()))
                .and(Specifications.RepositoryHasAreaBetween(paramData.getParam().getMinArea(), paramData.getParam().getMaxArea()))
                .and(Specifications.RepositoryHasCommunityBetween(paramData.getParam().getMinCommunity(), paramData.getParam().getMaxCommunity()))
                .and(Specifications.RepositoryHasSorterBetween(paramData.getParam().getMinSorter(), paramData.getParam().getMaxSorter()))
                .and(Specifications.RepositoryHasDeliverymanBetween(paramData.getParam().getMinDeliveryman(), paramData.getParam().getMaxDeliveryman()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Repository> repositoryPage = repositoryRepository.findAll(spec, pageable);
        return new ResponseData<>().success(repositoryPage);
    }
}
