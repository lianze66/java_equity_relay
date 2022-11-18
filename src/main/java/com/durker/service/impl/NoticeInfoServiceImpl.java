package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.NoticeInfo;
import com.durker.mapper.NoticeInfoMapper;
import com.durker.service.INoticeInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeInfoServiceImpl extends ServiceImpl<NoticeInfoMapper, NoticeInfo> implements INoticeInfoService {

    @Override
    public List<NoticeInfo> list() {
        QueryWrapper<NoticeInfo> query = new QueryWrapper<>();
        query.orderByDesc("update_time");
        return baseMapper.selectList(query);
    }
}
