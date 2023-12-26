package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@Data
public class AllTaskList extends BaseEntity
{
    /** 执行id */
    @Excel(name = "执行id")
    private Long exeId;

    /** 执行人id */
    @Excel(name = "执行人id")
    private Long executor;

    /** 执行人name */
    @Excel(name = "执行人名称")
    private String executorName;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 开始执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /** 结束执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 执行动态表单提交内容 */
    @Excel(name = "动态表单提交内容")
    private String formResult;

    /** 执行备注 */
    @Excel(name = "执行备注")
    private String exeRemark;

    /** 站点id */
    @Excel(name = "站点id")
    private String siteId;

    /** 角色id */
    @Excel(name = "角色id")
    private String roleId;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private Long taskType;

    /** 任务表单动态表单schema */
    @Excel(name = "任务表单动态表单schema")
    private String formData;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 任务创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskCreateTime;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String taskStatus;

    /** 任务备注 */
    @Excel(name = "任务备注")
    private String taskRemark;


    /** 站点坐标 */
    @Excel(name = "站点坐标")
    private String coordinates;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String siteName;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String formStatus;

}
