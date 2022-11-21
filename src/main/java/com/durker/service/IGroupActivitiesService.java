package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.GroupActivities;

import java.util.List;
import java.util.Map;

public interface IGroupActivitiesService extends IService<GroupActivities> {

    /**
     * 正在运行的活动
     *
     * @return
     */
    List<GroupActivities> activeList();

    /**
     * 状态列表
     *
     * @return
     */
    List<Map<String, String>> statusList();

}
