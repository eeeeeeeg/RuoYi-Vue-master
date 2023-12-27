package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskAddParams对象 zs_task_add_params
 * 
 * @author ruoyi
 * @date 2023-12-19
 */
public class ZsTaskAddParams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新增参数id */
    private Long id;

    /** 站点id */
    @Excel(name = "站点id")
    private String siteId;

    /** 角色id */
    @Excel(name = "角色id")
    private String roleId;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private String taskType;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskBeginTime;

    /** 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String name;

    /** 表单模板 */
    @Excel(name = "表单模板")
    private String formData;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String status;

    /** 任务周期 */
    @Excel(name = "任务周期")
    private Long taskRang;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSiteId(String siteId) 
    {
        this.siteId = siteId;
    }

    public String getSiteId() 
    {
        return siteId;
    }
    public void setRoleId(String roleId) 
    {
        this.roleId = roleId;
    }

    public String getRoleId() 
    {
        return roleId;
    }
    public void setTaskType(String taskType) 
    {
        this.taskType = taskType;
    }

    public String getTaskType() 
    {
        return taskType;
    }
    public void setTaskBeginTime(Date taskBeginTime) 
    {
        this.taskBeginTime = taskBeginTime;
    }

    public Date getTaskBeginTime() 
    {
        return taskBeginTime;
    }
    public void setTaskEndTime(Date taskEndTime) 
    {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskEndTime() 
    {
        return taskEndTime;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setFormData(String formData) 
    {
        this.formData = formData;
    }

    public String getFormData() 
    {
        return formData;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setTaskRang(Long taskRang) 
    {
        this.taskRang = taskRang;
    }

    public Long getTaskRang() 
    {
        return taskRang;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("siteId", getSiteId())
            .append("roleId", getRoleId())
            .append("taskType", getTaskType())
            .append("taskBeginTime", getTaskBeginTime())
            .append("taskEndTime", getTaskEndTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("name", getName())
            .append("formData", getFormData())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("taskRang", getTaskRang())
            .toString();
    }
}
