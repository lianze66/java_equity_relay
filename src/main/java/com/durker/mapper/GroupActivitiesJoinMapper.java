package com.durker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.durker.bean.GroupActivitiesJoin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GroupActivitiesJoinMapper extends BaseMapper<GroupActivitiesJoin> {

    @Select("select ifnull(sum(person_count),0) from bu_group_activities_join where group_activities_id=#{groupActivitiesId}")
    int joinCount(Integer groupActivitiesId);

}