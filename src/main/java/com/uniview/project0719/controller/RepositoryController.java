package com.uniview.project0719.controller;

import com.uniview.project0719.dto.RepositoryDTO;
import com.uniview.project0719.service.RepositoryService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
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
public class RepositoryController {
    @Autowired
    private RepositoryService repositoryService;

    @PostMapping("/getRepositoryList")
    public ResponseData<?> getRepositoryList(@RequestBody ParamData<RepositoryDTO> paramData) {
        return repositoryService.findRepositories(paramData);
    }
    @GetMapping("/getRepositoryDetail/{repositoryId}")
    public ResponseData<?> getRepositoryDetail(@PathVariable("repositoryId") Integer id){
        return repositoryService.findRepositoryDetail(id);
    }
}
