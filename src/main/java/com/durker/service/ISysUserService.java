package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.SysUser;

import java.util.Map;

public interface ISysUserService extends IService<SysUser> {

    Map<String, String> login(String loginName, String password);

    SysUser getUserInfo(String token);

}
