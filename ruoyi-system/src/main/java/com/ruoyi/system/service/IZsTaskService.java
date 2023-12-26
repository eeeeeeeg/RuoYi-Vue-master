package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.model.TaskReport;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.core.domain.model.ZsTask;
import org.quartz.SchedulerException;

/**
 * zs_taskService接口
 * 
 * @author ruoyi
 * @date 2023-12-08
 */
public interface IZsTaskService 
{
    /**
     * 查询zs_task
     * 
     * @param id zs_task主键
     * @return zs_task
     */
    public ZsTask selectZsTaskById(Long id);

    /**
     * 查询zs_task列表
     * 
     * @param zsTask zs_task
     * @return zs_task集合
     */
    public List<ZsTask> selectZsTaskList(ZsTask zsTask);

    public List<TaskReport> selectTaskReport(ZsTask zsTask);

    /**
     * 新增zs_task
     * 
     * @param zsTask zs_task
     * @return 结果
     */
    public int insertZsTask(ZsTask zsTask) throws SchedulerException, TaskException;

    /**
     * 修改zs_task
     * 
     * @param zsTask zs_task
     * @return 结果
     */
    public int updateZsTask(ZsTask zsTask,String userId) throws SchedulerException, TaskException;

    /**
     * 批量删除zs_task
     * 
     * @param ids 需要删除的zs_task主键集合
     * @return 结果
     */
    public int deleteZsTaskByIds(Long[] ids);

    /**
     * 删除zs_task信息
     * 
     * @param id zs_task主键
     * @return 结果
     */
    public int deleteZsTaskById(Long id);
}
