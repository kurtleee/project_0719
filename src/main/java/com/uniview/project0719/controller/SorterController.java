package com.uniview.project0719.controller;

import com.uniview.project0719.dto.SorterDTO;
import com.uniview.project0719.service.SorterService;
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
 * @date ：Created in 2024/7/26 14:42
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/sorter")
@Tag(name = "分拣员接口")
public class SorterController {
    @Autowired
    private SorterService sorterService;

    @PostMapping("/getSorterList")
    @Operation(summary = "获取当前仓库的配送员列表")
    public ResponseData<?> getSorterList(@RequestBody ParamData<SorterDTO> paramData) {
        return sorterService.findSorter(paramData);
    }
    @PostMapping("/getAvailableSorterList")
    @Operation(summary = "获取无所属仓库的分拣员列表")
    public ResponseData<?> getAvailableSorterList(@RequestBody ParamData<SorterDTO> paramData) {
        return sorterService.findSorterAvailable(paramData);
    }
    @PostMapping("/updateSorter")
    @Operation(summary = "修改分拣员状态或将配送员移出/入仓库",description = "将该行对象传至后端，修改状态则单独将status属性改变，移出仓库则将repositoryId设为null")
    public ResponseData<?> updateSorter(@RequestBody SorterDTO sorterDTO){
        return sorterService.updateSorter(sorterDTO);
    }
}
