package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.GroupActivities;
import com.durker.mapper.GroupActivitiesMapper;
import com.durker.service.IGroupActivitiesService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class IGroupActivitiesServiceImpl extends ServiceImpl<GroupActivitiesMapper, GroupActivities> implements IGroupActivitiesService {

    @Override
    public List<GroupActivities> activeList() {
        QueryWrapper<GroupActivities> query = new QueryWrapper<>();
        query.eq("status", "进行中"); // 未开始、进行中、已取消、已结束
        return baseMapper.selectList(query);
    }
}
