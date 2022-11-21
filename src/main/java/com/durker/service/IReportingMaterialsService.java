package com.durker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.durker.bean.ReportingMaterials;

import java.util.List;
import java.util.Map;

public interface IReportingMaterialsService extends IService<ReportingMaterials> {
    /**
     * 状态列表
     *
     * @return
     */
    List<Map<String, String>> statusList();

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
