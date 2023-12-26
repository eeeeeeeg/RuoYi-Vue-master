package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.AllTaskList;
import com.ruoyi.system.domain.ZsTaskExe;

/**
 * taskExeMapper接口
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
public interface ZsTaskExeMapper 
{
    /**
     * 查询taskExe
     * 
     * @param id taskExe主键
     * @return taskExe
     */
    public ZsTaskExe selectZsTaskExeById(Long id);

    /**
     * 查询taskExe列表
     * 
     * @param zsTaskExe taskExe
     * @return taskExe集合
     */
    public List<ZsTaskExe> selectZsTaskExeList(ZsTaskExe zsTaskExe);

    public List <AllTaskList> allTaskList(Long userId);

    /**
     * 新增taskExe
     * 
     * @param zsTaskExe taskExe
     * @return 结果
     */
    public int insertZsTaskExe(ZsTaskExe zsTaskExe);

    /**
     * 修改taskExe
     * 
     * @param zsTaskExe taskExe
     * @return 结果
     */
    public int updateZsTaskExe(ZsTaskExe zsTaskExe);

    public int editExeInfo(ZsTaskExe zsTaskExe);

    public int editExeInfo(AllTaskList allTaskList);

    /**
     * 删除taskExe
     * 
     * @param id taskExe主键
     * @return 结果
     */
    public int deleteZsTaskExeById(Long id);

    public int deleteZsTaskExeByTaskId(String taskId);

    /**
     * 批量删除taskExe
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsTaskExeByIds(Long[] ids);

    public int deleteZsTaskExeByTaskIds(String[] taskIds);
}
