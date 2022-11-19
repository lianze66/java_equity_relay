package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.GroupActivitiesJoin;
import com.durker.mapper.GroupActivitiesJoinMapper;
import com.durker.service.IGroupActivitiesJoinService;
import org.springframework.stereotype.Service;

@Service
public class GroupActivitiesJoinServiceImpl extends ServiceImpl<GroupActivitiesJoinMapper, GroupActivitiesJoin> implements IGroupActivitiesJoinService {

    @Override
    public boolean signUp(GroupActivitiesJoin groupActivitiesJoin) {
        QueryWrapper<GroupActivitiesJoin> query = new QueryWrapper<>();
        query.eq("group_activities_id", groupActivitiesJoin.getGroupActivitiesId());
        query.eq("sys_user_id", groupActivitiesJoin.getSysUserId());
        GroupActivitiesJoin join = baseMapper.selectOne(query);
        if (join == null) {
            baseMapper.insert(groupActivitiesJoin);
            return true;
        } else {
            return false;
        }
    }
}