package com.durker.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.durker.vo.JoinUserAndCount;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("群体活动模型")
@TableName("bu_group_activities")
public class GroupActivities {

    public enum column {
        MEETING_TIME, STATUS
    }

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("聚集时间")
    private String meetingTime;

    @ApiModelProperty("聚集地点")
    private String meetingAddress;

    @ApiModelProperty("重要提醒")
    private String remind;

    @ApiModelProperty("状态")
    private String status; // 未开始、进行中、已取消、已结束

    @ApiModelProperty("发起人ID")
    private Integer sponsorId;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    @ApiModelProperty("参加聚集总人数")
    private int joinCount;

    @TableField(exist = false)
    @ApiModelProperty("参加聚集人员及人数集合")
    private List<JoinUserAndCount> joinUserAndCountList;

}
