package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.ReportingMaterials;

import java.util.List;

public interface IReportingMaterialsService extends IService<ReportingMaterials> {
    /**
     * 公共资源库列表
     * @return
     */
    List<ReportingMaterials> showList();

    /**
     * 个人列表
     * @return
     */
    List<ReportingMaterials> ownList(String token);
}
