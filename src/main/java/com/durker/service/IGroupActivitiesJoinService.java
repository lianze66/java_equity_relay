package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.GroupActivitiesJoin;
import com.durker.vo.JoinUserAndCount;

import java.util.List;

public interface IGroupActivitiesJoinService extends IService<GroupActivitiesJoin> {

    /**
     * 我要报名
     *
     * @param groupActivitiesJoin
     * @return
     */
    boolean signUp(GroupActivitiesJoin groupActivitiesJoin);

    /**
     * 取消报名
     * @param groupActivitiesJoin
     * @return
     */
    boolean noSignUp(GroupActivitiesJoin groupActivitiesJoin);

    /**
     * 参加聚集人员及人数集合
     * @param groupActivitiesId
     * @return
     */
    List<JoinUserAndCount> joinUserList(Integer groupActivitiesId);

    /**
     * 此“接龙”参与人数
     *
     * @param groupActivitiesId “接龙ID”
     * @return
     */
    int joinCount(Integer groupActivitiesId);
}
