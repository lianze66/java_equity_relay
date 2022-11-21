package com.durker.controller;

import com.durker.bean.ReportingMaterials;
import com.durker.service.IReportingMaterialsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "报案材料接口")
@RequestMapping("reportingMaterials")
@RestController
public class ReportingMaterialsController {

    @Autowired
    private IReportingMaterialsService reportingMaterialsService;

    @ApiOperation("全部数据列表")
    @GetMapping("list")
    public List<ReportingMaterials> queryList() {
        return reportingMaterialsService.list();
    }

    @ApiOperation("待审核列表")
    @GetMapping("checkList")
    public List<ReportingMaterials> checkList() {
        return reportingMaterialsService.checkList();
    }

    @ApiOperation("审核通过列表")
    @GetMapping("showList")
    public List<ReportingMaterials> showList() {
        return reportingMaterialsService.showList();
    }

    @ApiOperation("个人列表")
    @GetMapping("ownList/{token}")
    public List<ReportingMaterials> ownList(@PathVariable String token) {
        return reportingMaterialsService.ownList(token);
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

    @ApiOperation("状态列表")
    @GetMapping("statusList")
    public List<Map<String, String>> statusList() {
        return reportingMaterialsService.statusList();
    }
}
