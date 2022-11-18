package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.GroupActivities;

import java.util.List;

public interface IGroupActivitiesService extends IService<GroupActivities> {

    /**
     * 正在运行的活动
     *
     * @return
     */
    List<GroupActivities> activeList();
}
