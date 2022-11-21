package com.durker.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("系统用户模型")
@TableName("bu_sys_user")
public class SysUser {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("登录名称")
    private String loginName;

    @JsonIgnore
    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("销售人员ID")
    private Integer salesmanId;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("合同编号")
    private String contractNo;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    @ApiModelProperty("销售人员名称")
    private String salesmanName;
}
