package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.AllTaskList;
import com.ruoyi.system.domain.TaskExeEditInfo;
import com.ruoyi.system.domain.ZsTaskExe;

/**
 * taskExeService接口
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
public interface IZsTaskExeService 
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

    public List <AllTaskList> allTaskList();

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

    public int editExeAllTaskInfo(TaskExeEditInfo taskExeEditInfo);

    public int editExeInfo(AllTaskList allTaskList);

    /**
     * 批量删除taskExe
     * 
     * @param ids 需要删除的taskExe主键集合
     * @return 结果
     */
    public int deleteZsTaskExeByIds(Long[] ids);

    /**
     * 删除taskExe信息
     * 
     * @param id taskExe主键
     * @return 结果
     */
    public int deleteZsTaskExeById(Long id);
}
