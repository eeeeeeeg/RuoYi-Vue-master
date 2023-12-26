package com.ruoyi.common.core.domain.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * zs_task对象 zs_task
 * 
 * @author ruoyi
 * @date 2023-12-08
 */
@Data
public class ZsTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    private Long id;

    /** 站点 */
    @Excel(name = "站点")
    private String siteId;

    /** 角色 */
    @Excel(name = "角色")
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

    /** 任务状态1待进行2.正在执行3.已经完成 */
    @Excel(name = "任务状态1待进行2.正在执行3.已经完成")
    private String status;

    /** 任务周期 */
    @Excel(name = "任务周期")
    private Integer taskRang;

    /** 是否生成定时任务*/
    @Excel(name = "是否生成定时任务")
    private Boolean ifGenertTime;

    /** 是否立即执行 */
    @Excel(name = "是否立即执行")
    private Boolean ifExe;

    private Long jobId;

    private String flag;

    private Long executor;

    private Long patrolDuration;

    private String formResult;

    private String patrolTime;

}
