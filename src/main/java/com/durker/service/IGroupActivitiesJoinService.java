package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.GroupActivitiesJoin;

public interface IGroupActivitiesJoinService extends IService<GroupActivitiesJoin> {

    boolean signUp(GroupActivitiesJoin groupActivitiesJoin);
}
