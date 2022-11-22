package com.durker.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.Salesman;
import com.durker.bean.SysUser;
import com.durker.mapper.SysUserMapper;
import com.durker.service.ISalesmanService;
import com.durker.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISalesmanService salesmanService;

    @Override
    public List<SysUser> list() {
        List<SysUser> list = baseMapper.queryList();
        for (SysUser sysUser : list) {
            sysUser.setSalesmanName(salesmanService.getById(sysUser.getSalesmanId()).getName());
        }
        return list;
    }

    @Override
    public SysUser getById(Serializable id) {
        SysUser sysUser = super.getById(id);
        sysUser.setSalesmanName(salesmanService.getById(sysUser.getSalesmanId()).getName());
        return sysUser;
    }

    @Override
    public Map<String, String> login(String loginName, String password) {
        Map<String, String> map = new HashMap<>();

        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("login_name", loginName);
        SysUser sysUser = baseMapper.selectOne(query);

        if (sysUser != null) {
            if (sysUser.getPassword().equals(password)) {
                String token = JWT.create().withAudience(sysUser.getId().toString()).sign(Algorithm.HMAC256(sysUser.getPassword()));
                map.put("code", "200");
                map.put("message", "登录成功！");
                map.put("token", token);
            } else {
                map.put("code", "000");
                map.put("message", "密码错误！");
            }
        } else {
            map.put("code", "000");
            map.put("message", "用户名不存在！");
        }
        return map;
    }

    @Override
    public SysUser getUserInfo(String token) {
        SysUser sysUser = null;
        if (token != null && !token.equals("")) {
            String id = JWT.decode(token).getAudience().get(0);

            log.info("获取用户信息，用户ID为：{}", id);

            if (id != null) {
                sysUser = baseMapper.selectById(id);

                Salesman salesman = salesmanService.getById(sysUser.getSalesmanId());
                if (salesman != null) {
                    sysUser.setSalesmanName(salesman.getName());
                }

                if (sysUser != null) {
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(sysUser.getPassword())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        throw new RuntimeException("401");
                    }
                }
            }
        }

        return sysUser;
    }
}
