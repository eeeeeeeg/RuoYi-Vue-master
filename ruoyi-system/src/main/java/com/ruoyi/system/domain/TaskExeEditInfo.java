package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 下发执行任务对象 TaskExeEditInfo
 *
 * @author ruoyi
 * @date 2023-12-12
 */
@Data
public class TaskExeEditInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 执行任务
     */
    private Long exeId;

    /**
     * 开始执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始执行时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /**
     * 结束执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束执行时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 表单保存结果
     */
    @Excel(name = "表单保存结果")
    private String formResult;


    @Excel(name = "表单状态")
    private String formStatus;

    /**
     * 任务id
     */
    @Excel(name = "任务id")
    private Long taskId;

    /**
     * 任务状态
     */
    @Excel(name = "任务状态")
    private String taskStatus;

    /**
     * 任务类型
     */
    @Excel(name = "任务类型")
    private String taskType;

    /**
     * 站点id
     */
    @Excel(name = "站点id")
    private String siteId;
}
