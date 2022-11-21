package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.GroupActivitiesJoin;

public interface IGroupActivitiesJoinService extends IService<GroupActivitiesJoin> {

    /**
     * 登记
     *
     * @param groupActivitiesJoin
     * @return
     */
    boolean signUp(GroupActivitiesJoin groupActivitiesJoin);

    /**
     * 此“接龙”参与人数
     *
     * @param groupActivitiesId “接龙ID”
     * @return
     */
    int joinCount(Integer groupActivitiesId);

}
