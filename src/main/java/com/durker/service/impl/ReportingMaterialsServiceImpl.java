package com.durker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.durker.bean.FileInfo;
import com.durker.bean.ReportingMaterials;
import com.durker.bean.ReportingMaterialsFile;
import com.durker.mapper.ReportingMaterialsMapper;
import com.durker.service.IFileInfoService;
import com.durker.service.IReportingMaterialsFileService;
import com.durker.service.IReportingMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportingMaterialsServiceImpl extends ServiceImpl<ReportingMaterialsMapper, ReportingMaterials> implements IReportingMaterialsService {

    @Autowired
    private IReportingMaterialsFileService reportingMaterialsFileService;

    @Autowired
    private IFileInfoService fileInfoService;

    @Override
    public boolean save(ReportingMaterials reportingMaterials) {
        materialsAndFileShip(reportingMaterials);
        return super.save(reportingMaterials);
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
}
