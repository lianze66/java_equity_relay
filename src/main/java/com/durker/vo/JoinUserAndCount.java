package com.durker.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("参加聚集人员及人数")
public class JoinUserAndCount {

    @ApiModelProperty("参加聚集人员")
    private String joinName;

    @ApiModelProperty("参加聚集人数")
    private Integer joinCount;

}
