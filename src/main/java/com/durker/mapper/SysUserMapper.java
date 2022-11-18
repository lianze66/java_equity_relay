package com.durker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.durker.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from bu_sys_user where login_name=#{loginName}")
    SysUser getByLoginName(String loginName);

}
