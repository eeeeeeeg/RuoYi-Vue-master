package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * taskExe对象 zs_task_exe
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
public class ZsTaskExe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 执行任务 */
    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** 执行人id */
    @Excel(name = "执行人id")
    private Long executor;

    /** 开始执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始执行时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /** 结束执行时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @Excel(name = "结束执行时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 表单保存结果 */
    @Excel(name = "表单保存结果")
    private String formResult;

    public String getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    @Excel(name = "表单状态")
    private String formStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setExecutor(Long executor) 
    {
        this.executor = executor;
    }

    public Long getExecutor() 
    {
        return executor;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setFormResult(String formResult) 
    {
        this.formResult = formResult;
    }

    public String getFormResult() 
    {
        return formResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("executor", getExecutor())
            .append("beginTime", getBeginTime())
            .append("endTime", getEndTime())
            .append("formResult", getFormResult())
            .append("remark", getRemark())
            .append("formStatus", getFormStatus())
            .toString();
    }
}
