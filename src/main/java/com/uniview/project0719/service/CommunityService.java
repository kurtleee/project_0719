package com.uniview.project0719.service;

import com.uniview.project0719.dto.CommunityDTO;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 16:19
 * @description：
 * @modified By：
 * @version: $
 */
public interface CommunityService {
    /**
     * 通过仓库id查询辐射的小区(用于仓库详情)
     * @param paramData
     * @return
     */
    ResponseData<?> findCommunities(ParamData<CommunityDTO> paramData);

    /**
     * 查询无所属仓库且与仓库在一个区的小区
     * @param paramData
     * @return
     */
    ResponseData<?> findCommunitiesAvailable(ParamData<CommunityDTO> paramData);

    /**
     * 用于将小区移出/入本仓库，只需将本行对象传入后端
     * @param communityDTO
     * @return
     */
    ResponseData<?> updateCommunity(CommunityDTO communityDTO);

    /**
     * 通过仓库id查询小区(用于添加地址)
     * @param id
     * @return
     */
    ResponseData<?> findAllCommunities(Integer id);
}
