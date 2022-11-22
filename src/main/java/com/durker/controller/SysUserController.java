package com.durker.controller;

import com.durker.bean.SysUser;
import com.durker.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "系统用户接口")
@RequestMapping("sysUser")
@RestController
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation("列表")
    @GetMapping("list")
    public List<SysUser> queryList() {
        return sysUserService.list();
    }

    @ApiOperation("更新")
    @ApiImplicitParams(@ApiImplicitParam(name = "sysUser", value = "对象", dataTypeClass = SysUser.class))
    @PutMapping("update")
    public boolean update(@RequestBody SysUser sysUser) {
        return sysUserService.updateById(sysUser);
    }

    @ApiOperation("删除")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "用户主键", dataTypeClass = Integer.class))
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        return sysUserService.removeById(id);
    }

    @ApiOperation("详情")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "用户主键", dataTypeClass = Integer.class))
    @GetMapping("{id}")
    public SysUser detail(@PathVariable Integer id) {
        return sysUserService.getById(id);
    }

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", dataTypeClass = String.class)
    })
    @PostMapping("login")
    public Map<String, String> login(@RequestParam String loginName, @RequestParam String password) {
        return sysUserService.login(loginName, password);
    }

    @ApiOperation("返回用户信息")
    @GetMapping("getUserInfo")
    public SysUser getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        return sysUserService.getUserInfo(token);
    }
}
