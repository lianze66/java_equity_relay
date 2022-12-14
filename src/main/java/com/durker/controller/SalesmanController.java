package com.durker.controller;

import com.durker.bean.Salesman;
import com.durker.service.ISalesmanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "销售人员接口")
@RequestMapping("salesman")
@RestController
public class SalesmanController {

    @Autowired
    private ISalesmanService salesmanService;

    @ApiOperation("列表")
    @GetMapping("list")
    public List<Salesman> queryList() {
        return salesmanService.list();
    }

    @ApiOperation("详情")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "主键", dataTypeClass = Integer.class))
    @GetMapping("{id}")
    public Salesman detail(@PathVariable Integer id) {
        return salesmanService.getById(id);
    }
}
