package com.durker.controller;

import com.durker.bean.SysRole;
import com.durker.service.ISysRoleService;
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

@Api(tags = "系统角色接口")
@RequestMapping("sysRole")
@RestController
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation("列表")
    @GetMapping("list")
    public List<SysRole> queryList() {
        return sysRoleService.list();
    }

    @ApiOperation("详情")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "主键", dataTypeClass = Integer.class))
    @GetMapping("{id}")
    public SysRole detail(@PathVariable Integer id) {
        return sysRoleService.getById(id);
    }

}
