package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.FileInfo;
import com.durker.bean.ReportingMaterials;
import com.durker.bean.ReportingMaterialsFile;
import com.durker.mapper.ReportingMaterialsMapper;
import com.durker.service.IFileInfoService;
import com.durker.service.IReportingMaterialsFileService;
import com.durker.service.IReportingMaterialsService;
import com.durker.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class ReportingMaterialsServiceImpl extends ServiceImpl<ReportingMaterialsMapper, ReportingMaterials> implements IReportingMaterialsService {

    @Autowired
    private IReportingMaterialsFileService reportingMaterialsFileService;

    @Autowired
    private IFileInfoService fileInfoService;

    @Autowired
    private ISysUserService sysUserService;

    @Value("${file.server}")
    private String fileServer;

    @Override
    public boolean save(ReportingMaterials reportingMaterials) {
        boolean save = super.save(reportingMaterials);
        if (save) {
            materialsAndFileShip(reportingMaterials);
        }
        return save;
    }

    @Override
    public ReportingMaterials getById(Serializable id) {
        ReportingMaterials reportingMaterials = super.getById(id);

        QueryWrapper<ReportingMaterialsFile> fileQuery = new QueryWrapper<>();
        fileQuery.eq("reporting_materials_id", id);
        List<ReportingMaterialsFile> reportingMaterialsFileList = reportingMaterialsFileService.list(fileQuery);

        List<String> filePathList = new ArrayList<>();
        if (reportingMaterialsFileList != null && !reportingMaterialsFileList.isEmpty()) {
            for (ReportingMaterialsFile reportingMaterialsFile : reportingMaterialsFileList) {
                filePathList.add(fileServer + fileInfoService.getById(reportingMaterialsFile.getFileInfoId()).getPath());
            }
        }
        reportingMaterials.setFilePathList(filePathList);

        return reportingMaterials;
    }

    @Override
    public boolean updateById(ReportingMaterials reportingMaterials) {
        // 删除关系表里的数据
        QueryWrapper<ReportingMaterialsFile> query = new QueryWrapper<>();
        query.eq("reporting_materials_id", reportingMaterials.getId());
        reportingMaterialsFileService.remove(query);
        materialsAndFileShip(reportingMaterials);
        return super.updateById(reportingMaterials);
    }

    /**
     * 保存“报案材料”的关系
     *
     * @param reportingMaterials 报案材料对象
     */
    private void materialsAndFileShip(ReportingMaterials reportingMaterials) {
        List<String> filePathList = reportingMaterials.getFilePathList();
        if (filePathList != null && !filePathList.isEmpty()) {
            for (String filePath : filePathList) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setPath(filePath);
                fileInfo.setUpdateTime(new Date());
                fileInfoService.save(fileInfo);

                ReportingMaterialsFile reportingMaterialsFile = new ReportingMaterialsFile();
                reportingMaterialsFile.setFileInfoId(fileInfo.getId());
                reportingMaterialsFile.setReportingMaterialsId(reportingMaterials.getId());
                reportingMaterialsFileService.save(reportingMaterialsFile);
            }
        }
    }

    @Override
    public List<Map<String, String>> statusList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("id", "1");
        map1.put("name", "私有");

        Map<String, String> map2 = new HashMap<>();
        map2.put("id", "2");
        map2.put("name", "申请公开");

        Map<String, String> map3 = new HashMap<>();
        map3.put("id", "3");
        map3.put("name", "审核通过");

        Map<String, String> map4 = new HashMap<>();
        map4.put("id", "4");
        map4.put("name", "审核不通过");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        return list;
    }

    @Override
    public List<ReportingMaterials> list() {
        QueryWrapper<ReportingMaterials> query = new QueryWrapper<>();
        query.orderByDesc("update_time");
        List<ReportingMaterials> list = baseMapper.selectList(query);
        return adapterList(list);
    }

    @Override
    public List<ReportingMaterials> checkList() {
        QueryWrapper<ReportingMaterials> query = new QueryWrapper<>();
        query.eq("status", "申请公开");
        query.orderByDesc("update_time");
        List<ReportingMaterials> list = baseMapper.selectList(query);
        return adapterList(list);
    }

    @Override
    public List<ReportingMaterials> showList() {
        QueryWrapper<ReportingMaterials> query = new QueryWrapper<>();
        query.eq("status", "审核通过");
        query.orderByDesc("update_time");
        List<ReportingMaterials> list = baseMapper.selectList(query);
        return adapterList(list);
    }

    @Override
    public List<ReportingMaterials> ownList(String token) {
        QueryWrapper<ReportingMaterials> query = new QueryWrapper<>();
        query.eq("sys_user_id", sysUserService.getUserInfo(token).getId());
        List<ReportingMaterials> list = baseMapper.selectList(query);
        return adapterList(list);
    }

    private List<ReportingMaterials> adapterList(List<ReportingMaterials> list) {
        for (ReportingMaterials reportingMaterials : list) {

            QueryWrapper<ReportingMaterialsFile> fileQuery = new QueryWrapper<>();
            fileQuery.eq("reporting_materials_id", reportingMaterials.getId());
            List<ReportingMaterialsFile> reportingMaterialsFileList = reportingMaterialsFileService.list(fileQuery);

            List<String> filePathList = new ArrayList<>();
            if (reportingMaterialsFileList != null && !reportingMaterialsFileList.isEmpty()) {
                for (ReportingMaterialsFile reportingMaterialsFile : reportingMaterialsFileList) {
                    filePathList.add(fileServer + fileInfoService.getById(reportingMaterialsFile.getFileInfoId()).getPath());
                }
            }
            reportingMaterials.setFilePathList(filePathList);
        }
        return list;
    }
}
