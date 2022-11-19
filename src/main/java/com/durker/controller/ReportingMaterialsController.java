package com.durker.controller;

import com.durker.bean.ReportingMaterials;
import com.durker.service.IReportingMaterialsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "报案材料接口")
@RequestMapping("reportingMaterials")
@RestController
public class ReportingMaterialsController {

    @Autowired
    private IReportingMaterialsService reportingMaterialsService;

    @ApiOperation("列表")
    @GetMapping("list")
    public List<ReportingMaterials> queryList() {
        return reportingMaterialsService.list();
    }

    @ApiOperation("保存")
    @PostMapping("insert")
    public boolean insert(@RequestBody ReportingMaterials reportingMaterials) {
        return reportingMaterialsService.save(reportingMaterials);
    }

    @ApiOperation("更新")
    @PutMapping("update")
    public boolean update(@RequestBody ReportingMaterials reportingMaterials) {
        return reportingMaterialsService.updateById(reportingMaterials);
    }

    @ApiOperation("详情")
    @GetMapping("{id}")
    public ReportingMaterials detail(@PathVariable Integer id) {
        return reportingMaterialsService.getById(id);
    }
}
