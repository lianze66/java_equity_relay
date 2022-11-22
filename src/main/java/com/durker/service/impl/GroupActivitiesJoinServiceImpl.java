package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.GroupActivitiesJoin;
import com.durker.mapper.GroupActivitiesJoinMapper;
import com.durker.service.IGroupActivitiesJoinService;
import com.durker.vo.JoinUserAndCount;
import org.springframework.stereotype.Service;

import java.util.List;

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
        } else if (join.getPersonCount().intValue() != groupActivitiesJoin.getPersonCount().intValue()) {
            baseMapper.updateById(groupActivitiesJoin);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<JoinUserAndCount> joinUserList(Integer groupActivitiesId) {
        return baseMapper.joinUserList(groupActivitiesId);
    }

    @Override
    public int joinCount(Integer groupActivitiesId) {
        return baseMapper.joinCount(groupActivitiesId);
    }
}
