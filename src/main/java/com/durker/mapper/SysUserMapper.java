package com.durker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.durker.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select t1.*,t2.name salesmanName from bu_sys_user t1 left join bu_salesman t2 on t1.salesman_id=t2.id")
    List<SysUser> queryList();

}
