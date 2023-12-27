package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;


@Data
public class TaskReport extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 人员名称
     */
    @Excel(name = "人员名称")
    private String executor;

    /**
     * 巡检站点
     */
    @Excel(name = "巡检站点")
    private String siteName;

    /**
     * 所属部门
     */
    @Excel(name = "所属部门")
    private String dept;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phonenumber;

    /**
     * 巡检日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "巡检日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date patrolTime;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskBeginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;

    /**
     * 巡检时长
     */
    @Excel(name = "巡检时长")
    private String patrolDuration;

    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    private String taskName;
}
