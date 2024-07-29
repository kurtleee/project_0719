package com.uniview.project0719.controller;

import com.uniview.project0719.dto.SortDTO;
import com.uniview.project0719.service.SortService;
import com.uniview.project0719.utils.ParamData;
import com.uniview.project0719.utils.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @Author：lixin
 * @Package：com.uniview.project0719.controller
 * @Project：project_0719
 * @name：SortController
 * @Date：2024/7/26 12:16
 * @Filename：SortController
 */
@RestController
@RequestMapping("/sort")
@Tag(name = "SortController", description = "分拣监控控制器")
public class SortController {
    @Autowired
    private SortService sortService;

    @PostMapping("/getSortList")
    @Operation(summary="获取分拣列表")
    public ResponseData<?> getSortList(@RequestBody ParamData<SortDTO> paramData) {
        return sortService.findAllSorts(paramData);
    }
    @PostMapping("/getSorterSortList")
    @Operation(summary = "获取分拣员端的待分拣列表，即接单中心页面",description = "只需传入分页参数")
    public ResponseData<?> getSorterSortList(@RequestBody ParamData<SortDTO> paramData){
        try {
            return sortService.findSortsByRepository(paramData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/getCurrentSorterList")
    @Operation(summary = "我的任务界面",description = "传入分页参数及status")
    public ResponseData<?> getCurrentSorterList(@RequestBody ParamData<SortDTO> paramData){
        try {
            return sortService.findSortByStatus(paramData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
