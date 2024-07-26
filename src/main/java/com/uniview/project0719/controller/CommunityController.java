package com.uniview.project0719.controller;

import com.uniview.project0719.dto.CommunityDTO;
import com.uniview.project0719.service.CommunityService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zx
 * @date ：Created in 2024/7/26 16:59
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/community")
@Tag(name = "小区接口")
public class CommunityController {
    @Autowired
    private CommunityService communityService;
    @PostMapping("/getCommunityList")
    public ResponseData<?> getCommunityList(@RequestBody ParamData<CommunityDTO> paramData){
        return communityService.findCommunities(paramData);
    }
    @PostMapping("/getCommunityAvailable")
    public ResponseData<?> getCommunityAvailable(@RequestBody ParamData<CommunityDTO> paramData){
        return communityService.findCommunitiesAvailable(paramData);
    }
    @PostMapping("/updateCommunity")
    @Operation(summary = "将小区移出/入本仓库",description = "移出仓库将repositoryId置为null")
    public ResponseData<?> updateCommunity(@RequestBody CommunityDTO communityDTO){
        return communityService.updateCommunity(communityDTO);
    }
}
