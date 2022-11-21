package com.durker.controller;

import com.durker.bean.GroupActivities;
import com.durker.bean.GroupActivitiesJoin;
import com.durker.service.IGroupActivitiesJoinService;
import com.durker.service.IGroupActivitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "群体活动接口")
@RequestMapping("groupActivities")
@RestController
public class GroupActivitiesController {

    @Autowired
    private IGroupActivitiesService groupActivitiesService;

    @Autowired
    private IGroupActivitiesJoinService groupActivitiesJoinService;

    @ApiOperation("列表")
    @GetMapping("list")
    public List<GroupActivities> queryList() {
        return groupActivitiesService.list();
    }

    @ApiOperation("正在“运行中”的列表")
    @GetMapping("activeList")
    public List<GroupActivities> activeList() {
        return groupActivitiesService.activeList();
    }

    @ApiOperation("保存")
    @PostMapping("insert")
    public boolean insert(@RequestBody GroupActivities groupActivities) {
        return groupActivitiesService.save(groupActivities);
    }

    @ApiOperation("更新")
    @PutMapping("update")
    public boolean update(@RequestBody GroupActivities groupActivities) {
        return groupActivitiesService.updateById(groupActivities);
    }

    @ApiOperation("报名")
    @PutMapping("signUp")
    public boolean signUp(@RequestBody GroupActivitiesJoin groupActivitiesJoin) {
        return groupActivitiesJoinService.signUp(groupActivitiesJoin);
    }

    @ApiOperation("详情")
    @GetMapping("{id}")
    public GroupActivities detail(@PathVariable Integer id) {
        return groupActivitiesService.getById(id);
    }

    @ApiOperation("状态列表")
    @GetMapping("statusList")
    public List<Map<String, String>> statusList() {
        return groupActivitiesService.statusList();
    }
}
