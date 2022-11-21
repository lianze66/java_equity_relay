package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.GroupActivities;
import com.durker.mapper.GroupActivitiesMapper;
import com.durker.service.IGroupActivitiesJoinService;
import com.durker.service.IGroupActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupActivitiesServiceImpl extends ServiceImpl<GroupActivitiesMapper, GroupActivities> implements IGroupActivitiesService {

    @Autowired
    private IGroupActivitiesJoinService groupActivitiesJoinService;

    @Override
    public List<GroupActivities> activeList() {
        QueryWrapper<GroupActivities> query = new QueryWrapper<>();
        query.eq(GroupActivities.column.STATUS.name().toLowerCase(), "进行中"); // 未开始、进行中、已取消、已结束
        query.orderByAsc(GroupActivities.column.MEETING_TIME.name().toLowerCase());

        List<GroupActivities> list = baseMapper.selectList(query);
        return listAdapter(list);
    }

    @Override
    public List<GroupActivities> list() {
        return listAdapter(super.list());
    }

    @Override
    public GroupActivities getById(Serializable id) {
        GroupActivities groupActivities = super.getById(id);
        groupActivities.setJoinCount(groupActivitiesJoinService.joinCount(groupActivities.getId()));
        return groupActivities;
    }

    private List<GroupActivities> listAdapter(List<GroupActivities> list) {
        for (GroupActivities groupActivities: list) {
            groupActivities.setJoinCount(groupActivitiesJoinService.joinCount(groupActivities.getId()));
        }
        return list;
    }

    @Override
    public List<Map<String, String>> statusList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("id", "1");
        map1.put("name", "未开始");

        Map<String, String> map2 = new HashMap<>();
        map2.put("id", "2");
        map2.put("name", "进行中");

        Map<String, String> map3 = new HashMap<>();
        map3.put("id", "3");
        map3.put("name", "已取消");

        Map<String, String> map4 = new HashMap<>();
        map4.put("id", "4");
        map4.put("name", "已结束");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        return list;
    }
}
