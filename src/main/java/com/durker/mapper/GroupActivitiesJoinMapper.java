package com.durker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.durker.bean.GroupActivitiesJoin;
import com.durker.vo.JoinUserAndCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupActivitiesJoinMapper extends BaseMapper<GroupActivitiesJoin> {

    @Select("select ifnull(sum(person_count),0) from bu_group_activities_join where group_activities_id=#{groupActivitiesId}")
    int joinCount(Integer groupActivitiesId);

    @Select("select (select real_name from bu_sys_user where id=sys_user_id) join_name,person_count join_count from bu_group_activities_join where group_activities_id=#{groupActivitiesId}")
    List<JoinUserAndCount> joinUserList(Integer groupActivitiesId);

}