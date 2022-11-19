package com.durker.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("报案材料与文件关系模型")
@TableName("bu_reporting_materials_file")
public class ReportingMaterialsFile {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("报案材料表主键")
    private Integer ReportingMaterialsId;

    @ApiModelProperty("文件信息表主键")
    private Integer fileInfoId;

}
