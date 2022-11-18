package com.durker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.NoticeInfo;
import com.durker.mapper.NoticeInfoMapper;
import com.durker.service.INoticeInfoService;
import org.springframework.stereotype.Service;

@Service
public class NoticeInfoServiceImpl extends ServiceImpl<NoticeInfoMapper, NoticeInfo> implements INoticeInfoService {

}
