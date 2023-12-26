package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.model.TaskReport;
import com.ruoyi.common.core.domain.model.ZsTask;

import java.util.List;

/**
 * zs_taskMapper接口
 *
 * @author ruoyi
 * @date 2023-12-08
 */
public interface ZsTaskMapper {
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
    public int insertZsTask(ZsTask zsTask);

    /**
     * 修改zs_task
     *
     * @param zsTask zs_task
     * @return 结果
     */
    public int updateZsTask(ZsTask zsTask);

    public int updateZsTaskStatus(ZsTask zsTask);

    /**
     * 删除zs_task
     *
     * @param id zs_task主键
     * @return 结果
     */
    public int deleteZsTaskById(Long id);

    /**
     * 批量删除zs_task
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsTaskByIds(Long[] ids);
}
