package com.uniview.project0719.service;

import com.uniview.project0719.dto.SortDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

import java.text.ParseException;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.service
 * @Project：project_0719
 * @name：SortService
 * @Date：2024/7/26 14:04
 * @Filename：SortService
 */
public interface SortService {
    /**
     * 后台系统分拣监控表
     * @param paramData
     * @return
     */
    ResponseData<?> findAllSorts(ParamData<SortDTO> paramData);

    /**
     * 查询当前分拣员小程序端待分拣订单
     * @param paramData
     * @return
     * @throws ParseException
     */
    ResponseData<?> findSortsByRepository(ParamData<SortDTO> paramData) throws ParseException;

    /**
     * 通过分拣表状态查询当前分拣员的分拣记录
     * @param paramData
     * @return
     */
    ResponseData<?> findSortByStatus(ParamData<SortDTO> paramData) throws ParseException;
}
