package com.uniview.project0719.controller;

import com.uniview.project0719.dto.RepositoryDTO;
import com.uniview.project0719.service.RepositoryService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：zx
 * @date ：Created in 2024/7/25 19:12
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/repository")
@Tag(name = "仓库接口")
public class RepositoryController {
    @Autowired
    private RepositoryService repositoryService;

    @PostMapping("/getRepositoryList")
    @Operation(summary = "获取仓库列表")
    public ResponseData<?> getRepositoryList(@RequestBody ParamData<RepositoryDTO> paramData) {
        return repositoryService.findRepositories(paramData);
    }
    @GetMapping("/getRepositoryDetail/{repositoryId}")
    @Operation(summary = "获取仓库详情")
    public ResponseData<?> getRepositoryDetail(@PathVariable("repositoryId") Integer id){
        return repositoryService.findRepositoryDetail(id);
    }
}
