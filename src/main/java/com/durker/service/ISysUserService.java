package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.SysUser;

import java.util.Map;

public interface ISysUserService extends IService<SysUser> {
    /**
     * 登录
     * @param loginName 登录名称
     * @param password 密码
     * @return
     */
    Map<String, String> login(String loginName, String password);

    /**
     * 通过 token 值获取用户信息
     * @param token token值
     * @return
     */
    SysUser getUserInfo(String token);

    /**
     * 更新密码
     *
     * @param loginName 登录名称
     * @param oldPassword 老密码
     * @param newPassword 新密码
     *
     * @return 返回是否成功的结果
     */
    boolean updatePassword(String loginName, String oldPassword, String newPassword);
}
