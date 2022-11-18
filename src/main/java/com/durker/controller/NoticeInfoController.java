package com.durker.controller;

import com.durker.bean.NoticeInfo;
import com.durker.service.INoticeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Api(tags = "公告信息接口")
@RequestMapping("noticeInfo")
@RestController
public class NoticeInfoController {

    @Autowired
    private INoticeInfoService noticeInfoService;

    @ApiOperation("列表")
    @GetMapping("list")
    public List<NoticeInfo> queryList() {
        return noticeInfoService.list();
    }

    @ApiOperation("更新")
    @PutMapping("update")
    public void update(@RequestBody NoticeInfo noticeInfo) {
        noticeInfoService.updateById(noticeInfo);
    }

    @ApiOperation("删除")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "主键"))
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        noticeInfoService.removeById(id);
    }

    @ApiOperation("详情")
    @GetMapping("{id}")
    public NoticeInfo detail(@PathVariable Integer id) {
        return noticeInfoService.getById(id);
    }
}
