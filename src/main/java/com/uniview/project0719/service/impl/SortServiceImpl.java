package com.uniview.project0719.service.impl;

import com.uniview.project0719.dto.OrderItemResponseDTO;
import com.uniview.project0719.dto.RepositoryResponseDTO;
import com.uniview.project0719.dto.SortDTO;
import com.uniview.project0719.dto.SortResponseDTO;
import com.uniview.project0719.entity.Repository;
import com.uniview.project0719.entity.Sort;
import com.uniview.project0719.repository.SortRepository;
import com.uniview.project0719.repository.SorterRepository;
import com.uniview.project0719.service.SortService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import com.uniview.project0719.utils.Specifications;
import com.uniview.project0719.utils.UserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.service
 * @Project：project_0719
 * @name：SortServiceImpl
 * @Date：2024/7/26 15:09
 * @Filename：SortServiceImpl
 */
@Service
public class SortServiceImpl implements SortService {
    @Autowired
    private SortRepository sortRepository;
    @Autowired
    private SorterRepository sorterRepository;

    @Override
    public ResponseData<?> findAllSorts(ParamData<SortDTO> paramData) {
        Specification<Sort> spec = Specification.where(Specifications.sortHasOrderNumLike(paramData.getParam().getOrderNumOrSorterNameOrRepositoryId()))
                .or(Specifications.sortHasSorterNameLike(paramData.getParam().getOrderNumOrSorterNameOrRepositoryId()))
                .or(Specifications.sortHasRepositoryNameLike(paramData.getParam().getOrderNumOrSorterNameOrRepositoryId()))
                .and(Specifications.sortHasStatus(paramData.getParam().getStatus()))
                .and(Specifications.sortHasCity(paramData.getParam().getCity()))
                .and(Specifications.sortHasRegion(paramData.getParam().getRegion()))
                .and(Specifications.sortHasOrderTimeBetween(paramData.getParam().getMinOrderTime(), paramData.getParam().getMaxOrderTime()))
                .and(Specifications.sortHasGoodsNumBetween(paramData.getParam().getMinGoodsNum(), paramData.getParam().getMaxGoodsNum()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Sort> sortPage = sortRepository.findAll(spec, pageable);
        List<SortResponseDTO> resultList = new ArrayList<>();
        sortPage.getContent().forEach(e -> {
            SortResponseDTO sortResponseDTO = new SortResponseDTO();
            BeanUtils.copyProperties(e, sortResponseDTO);
            sortResponseDTO.setOrderNum(e.getUserOrder().getOrderId());
            sortResponseDTO.setOrderTime(e.getUserOrder().getOrderDate());
            sortResponseDTO.setGoodsNum(e.getUserOrder().getOrderItems().size());
            sortResponseDTO.setSortTime(Duration.between(e.getUserOrder().getOrderDate(), e.getSubmitTime()).toMinutes() + "分钟");
            sortResponseDTO.setSorterName(e.getSorter().getName());
            sortResponseDTO.setCity(e.getSorter().getRepository().getCity());
            sortResponseDTO.setRegion(e.getSorter().getRepository().getRegion());
            sortResponseDTO.setRepositoryName(e.getSorter().getRepository().getName());
            resultList.add(sortResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", sortPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findSortsByRepository(ParamData<SortDTO> paramData) throws ParseException {
        Integer sorterId = UserContext.getUserId();// 获得当前分拣员的id
        Integer repositoryId = sorterRepository.findSorterById(sorterId).getRepository().getId();// 获得当前分拣员所属仓库的id
        Specification<Sort> spec = Specification.where(Specifications.sortHasRepositoryId(repositoryId)).and(Specifications.sortHasStatus(2));// 通过仓库id和状态查询分拣表中符合条件的记录
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Sort> sortPage = sortRepository.findAll(spec, pageable);
        List<SortResponseDTO> resultList = new ArrayList<>();
        sortPage.getContent().forEach(e -> {
            SortResponseDTO sortResponseDTO = new SortResponseDTO();
            BeanUtils.copyProperties(e, sortResponseDTO);
            sortResponseDTO.setOrderNum(e.getUserOrder().getOrderId());
            sortResponseDTO.setGoodsNum(e.getUserOrder().getOrderItems().size());
            sortResponseDTO.setAddress(e.getUserOrder().getAddress().getDetailAddress());
            sortResponseDTO.setPhone(e.getUserOrder().getAddress().getPhone());
            sortResponseDTO.setReceiver(e.getUserOrder().getAddress().getReceiver());
            resultList.add(sortResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", sortPage.getTotalElements());
        return new ResponseData<>().success(map);
    }

    @Override
    public ResponseData<?> findSortByStatus(ParamData<SortDTO> paramData) throws ParseException {
        Specification<Sort> spec = Specification.where(Specifications.sortHasSorterId(UserContext.getUserId())).and(Specifications.sortHasStatus(paramData.getParam().getStatus()));
        Pageable pageable = PageRequest.of(paramData.getPage() - 1, paramData.getSize());
        Page<Sort> sortPage = sortRepository.findAll(spec, pageable);
        List<SortResponseDTO> resultList = new ArrayList<>();
        sortPage.getContent().forEach(e -> {
            SortResponseDTO sortResponseDTO = new SortResponseDTO();
            BeanUtils.copyProperties(e, sortResponseDTO);
            sortResponseDTO.setOrderNum(e.getUserOrder().getOrderId());
            sortResponseDTO.setGoodsNum(e.getUserOrder().getOrderItems().size());
            sortResponseDTO.setAddress(e.getUserOrder().getAddress().getDetailAddress());
            sortResponseDTO.setPhone(e.getUserOrder().getAddress().getPhone());
            sortResponseDTO.setReceiver(e.getUserOrder().getAddress().getReceiver());
            List<OrderItemResponseDTO> orderItems = new ArrayList<>();
            e.getUserOrder().getOrderItems().forEach(o -> {
                OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
                BeanUtils.copyProperties(o, orderItemResponseDTO);
                orderItems.add(orderItemResponseDTO);
            });
            sortResponseDTO.setOrderItems(orderItems);
            resultList.add(sortResponseDTO);
        });
        Map map = new HashMap<>();
        map.put("resultList", resultList);
        map.put("total", sortPage.getTotalElements());
        return new ResponseData<>().success(map);
    }
}
