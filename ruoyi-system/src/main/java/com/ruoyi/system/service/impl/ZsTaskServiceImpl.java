package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.ZsTask;
import com.ruoyi.system.domain.ZsTaskExe;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.ZsTaskExeMapper;
import com.ruoyi.system.mapper.ZsTaskMapper;
import com.ruoyi.system.service.IZsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * zs_taskService业务层处理
 *
 * @author ruoyi
 * @date 2023-12-08
 */
@Service
public class ZsTaskServiceImpl implements IZsTaskService {
    @Autowired
    private ZsTaskMapper zsTaskMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private ZsTaskExeMapper zsTaskExeMapper;

    /**
     * 查询zs_task
     *
     * @param id zs_task主键
     * @return zs_task
     */
    @Override
    public ZsTask selectZsTaskById(Long id) {
        return zsTaskMapper.selectZsTaskById(id);
    }

    /**
     * 查询zs_task列表
     *
     * @param zsTask zs_task
     * @return zs_task
     */
    @Override
    public List<ZsTask> selectZsTaskList(ZsTask zsTask) {
        return zsTaskMapper.selectZsTaskList(zsTask);
    }

    /**
     * 新增zs_task
     *
     * @param zsTask zs_task
     * @return 结果
     */
    @Override
    @Transactional
    public int insertZsTask(ZsTask zsTask) {
        int num = 0;
        List<String> roleIds = Arrays.asList(zsTask.getRoleId().split(","));

        // 根据角色id，获取用户列表
        List<SysUserRole> list = sysUserRoleMapper.selectUserListByRoleId(roleIds);

        zsTask.setStatus("1");
        zsTask.setCreateBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
        zsTask.setCreateTime(DateUtils.getNowDate());
        zsTaskMapper.insertZsTask(zsTask);

        Long generatedKey = zsTask.getId();
        System.out.printf(String.valueOf(generatedKey));


        for (int i = 0; i < list.size(); i++) {
            ZsTaskExe zsTaskExe = new ZsTaskExe();
            zsTaskExe.setTaskId(zsTask.getId());
            zsTaskExe.setExecutor(list.get(i).getUserId());
            num = zsTaskExeMapper.insertZsTaskExe(zsTaskExe);
        }
        return num;
    }

    /**
     * 修改zs_task
     *
     * @param zsTask zs_task
     * @return 结果
     */
    @Override
    public int updateZsTask(ZsTask zsTask, String userId) {
        zsTask.setUpdateBy(userId);
        zsTask.setUpdateTime(DateUtils.getNowDate());
        return zsTaskMapper.updateZsTask(zsTask);
    }

    /**
     * 批量删除zs_task
     *
     * @param ids 需要删除的zs_task主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskByIds(Long[] ids) {
        return zsTaskMapper.deleteZsTaskByIds(ids);
    }

    /**
     * 删除zs_task信息
     *
     * @param id zs_task主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskById(Long id) {
        return zsTaskMapper.deleteZsTaskById(id);
    }
}
