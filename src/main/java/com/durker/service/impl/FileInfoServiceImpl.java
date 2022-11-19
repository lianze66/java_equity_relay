package com.durker.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.FileInfo;
import com.durker.mapper.FileInfoMapper;
import com.durker.service.IFileInfoService;
import org.springframework.stereotype.Service;

@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {

}
