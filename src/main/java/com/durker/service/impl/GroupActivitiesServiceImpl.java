package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.GroupActivities;
import com.durker.mapper.GroupActivitiesMapper;
import com.durker.service.IGroupActivitiesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupActivitiesServiceImpl extends ServiceImpl<GroupActivitiesMapper, GroupActivities> implements IGroupActivitiesService {

    @Override
    public List<GroupActivities> activeList() {
        QueryWrapper<GroupActivities> query = new QueryWrapper<>();
        query.eq(GroupActivities.column.STATUS.name().toLowerCase(), "进行中"); // 未开始、进行中、已取消、已结束
        query.orderByAsc(GroupActivities.column.MEETING_TIME.name().toLowerCase());
        return baseMapper.selectList(query);
    }

    @Override
    public List<String> statusList() {
        List<String> list = new ArrayList<>();
        list.add("未开始");
        list.add("进行中");
        list.add("已取消");
        list.add("已结束");
        return list;
    }
}
