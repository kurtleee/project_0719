package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.CommunityDTO;
import com.uniview.project0719.entity.Community;
import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.repository.CommunityRepository;
import com.uniview.project0719.repository.RepositoryRepository;
import com.uniview.project0719.service.CommunityService;
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
 * @date ：Created in 2024/7/26 16:22
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private RepositoryRepository repositoryRepository;
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public ResponseData<?> findCommunities(ParamData<CommunityDTO> paramData) {
        Repository repository = repositoryRepository.findRepositoryById(paramData.getParam().getRepositoryId());
        Specification<Community> spec = Specification.where(Specifications.communityHasNameLike(paramData.getParam().getName()))
                .and(Specifications.communityHasRepository(repository));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Community> communityPage = communityRepository.findAll(spec, pageable);
        List<CommunityDTO> resultList = new ArrayList<>();
        communityPage.getContent().forEach(e -> {
            CommunityDTO communityDTO = new CommunityDTO();
            BeanUtils.copyProperties(e, communityDTO);
            communityDTO.setRepositoryId(e.getRepository().getId());
            communityDTO.setRegion(repository.getRegion());
            communityDTO.setCity(repository.getCity());
            resultList.add(communityDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", communityPage.getTotalPages());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findCommunitiesAvailable(ParamData<CommunityDTO> paramData) {
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Community> communityPage = communityRepository.findCommunitiesByRegionAndRepositoryIsNull(paramData.getParam().getRegion(), pageable);
        Map map = new HashMap<>();
        map.put("resultList", communityPage.getContent());
        map.put("total", communityPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> updateCommunity(CommunityDTO communityDTO) {
        Community community = communityRepository.findCommunityById(communityDTO.getId());
        if (communityDTO.getRepositoryId() == null) {
            community.setRepository(null);
        } else {
            Repository repository = repositoryRepository.findRepositoryById(communityDTO.getRepositoryId());
            community.setRepository(repository);
        }
        communityRepository.save(community);
        return new ResponseData<>().success();
    }

    @Override
    public ResponseData<?> findAllCommunities(Integer id) {
        List<Community> communitiesByRepository = communityRepository.findCommunitiesByRepository(repositoryRepository.findRepositoryById(id));
        return new ResponseData<>().success(communitiesByRepository);
    }

}
