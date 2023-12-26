package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.model.TaskReport;
import com.ruoyi.common.core.domain.model.ZsTask;
import com.ruoyi.common.enums.taskType;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.utils.DateTransUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.quartz.controller.SysJobController;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.ZsTaskAddParams;
import com.ruoyi.system.domain.ZsTaskExe;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.ZsTaskAddParamsMapper;
import com.ruoyi.system.mapper.ZsTaskExeMapper;
import com.ruoyi.system.mapper.ZsTaskMapper;
import com.ruoyi.system.service.IZsTaskService;
import org.quartz.SchedulerException;
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

    @Autowired
    private ZsTaskAddParamsMapper zsTaskAddParamsMapper;

    @Autowired
    private SysJobController controller;

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

    @Override
    public List<TaskReport> selectTaskReport(ZsTask zsTask) {
        return zsTaskMapper.selectTaskReport(zsTask);
    }

    /**
     * 新增zs_task
     *
     * @param zsTask zs_task
     * @return 结果
     */
    @Override
    @Transactional
    public int insertZsTask(ZsTask zsTask) throws TaskException, SchedulerException {
        int num = 0;


        if (zsTask.getIfExe().equals(true)) {
            List<String> roleIds = Arrays.asList(zsTask.getRoleId().split(","));
            // 根据角色id，获取用户列表
            List<SysUserRole> list = sysUserRoleMapper.selectUserListByRoleId(roleIds);
            zsTask.setStatus("1");
            if (StringUtils.isNotEmpty(zsTask.getFlag()) && zsTask.getFlag().equals("1")) {
                zsTask.setCreateBy(zsTask.getCreateBy());
                zsTask.setCreateTime(zsTask.getCreateTime());
            } else {
                zsTask.setCreateBy(String.valueOf(SecurityUtils.getLoginUser().getUserId()));
                zsTask.setCreateTime(DateUtils.getNowDate());
            }
            zsTask.setTaskRang(DateTransUtils.getDateValue(DateTransUtils.dateToString(zsTask.getCreateTime()), Integer.valueOf(zsTask.getTaskType())));
            zsTaskMapper.insertZsTask(zsTask);

            for (int i = 0; i < list.size(); i++) {
                ZsTaskExe zsTaskExe = new ZsTaskExe();
                zsTaskExe.setTaskId(zsTask.getId());
                zsTaskExe.setExecutor(list.get(i).getUserId());
                num = zsTaskExeMapper.insertZsTaskExe(zsTaskExe);
            }
            //  执行对应的定时任务

        }


        //是否生成定时任务 是否立即执行
        if (zsTask.getIfGenertTime().equals(true)) {
            //  生成对应的定时任务
            ZsTaskAddParams zsTaskAddParams = new ZsTaskAddParams();
            zsTaskAddParams.setName(zsTask.getName());
            zsTaskAddParams.setTaskType(zsTask.getTaskType());
            zsTaskAddParams.setSiteId(zsTask.getSiteId());
            zsTaskAddParams.setRoleId(zsTask.getRoleId());
            zsTaskAddParams.setRemark(zsTask.getRemark());
            zsTaskAddParams.setFormData(zsTask.getFormData());
            zsTaskAddParamsMapper.insertZsTaskAddParams(zsTaskAddParams);

            SysJob job = new SysJob();
            job.setJobName(zsTask.getName() + taskType.fromStatus(zsTask.getTaskType()).getMessage() + zsTaskAddParams.getCreateTime());
            job.setCronExpression(taskType.fromConfName(zsTask.getTaskType()).getConfName());
            job.setInvokeTarget("com.ruoyi.system.service.impl.ZsTaskServiceImpl.queryQuartz(+" + zsTaskAddParams.getId() + ")");
            job.setStatus("0");
            job.setMisfirePolicy("2");
            /*job.setTaskFlag("1");
            job.setTaskId(zsTask.getId());*/
            controller.add(job);
//
//    id = --
//    job.add({
//            concurrent:1,
//            cronExpression:"12-13 * * * * ? 2023/1",
//            invokeTarget:"ZsTaskServiceImpl.foo("+ id +")",
//            jobName:"t",
//            misfirePolicy:1,
//            status:"0"
//    })

        }
        return num;
    }

    public ZsTaskAddParams queryQuartz(Long id) throws SchedulerException, TaskException {
        ZsTaskAddParams zsTaskAddParams1 = zsTaskAddParamsMapper.selectZsTaskAddParamsById(id);
        ZsTask zsTask = new ZsTask();
        zsTask.setIfGenertTime(false);
        zsTask.setIfExe(true);
        insertZsTask(zsTask);
        return zsTaskAddParams1;
    }


    /**
     * 修改zs_task
     *
     * @param zsTask zs_task
     * @return 结果
     */
    @Override
    @Transactional
    public int updateZsTask(ZsTask zsTask, String userId) throws SchedulerException, TaskException {
        int num = 0;
        zsTask.setUpdateBy(userId);
        zsTask.setUpdateTime(DateUtils.getNowDate());

        ZsTask zsTaskResp = zsTaskMapper.selectZsTaskById(zsTask.getId());


        zsTask.setFlag("1");
        zsTask.setCreateBy(zsTaskResp.getCreateBy());
        zsTask.setCreateTime(zsTaskResp.getCreateTime());
        zsTaskMapper.deleteZsTaskById(zsTask.getId());


        /*ZsTaskExe zsTaskExe = new ZsTaskExe();
        zsTaskExe.setTaskId(zsTask.getId());
        List<ZsTaskExe> zsTaskExeList = zsTaskExeMapper.selectZsTaskExeList(zsTaskExe);

        ZsTaskExe addZsTaskExe = new ZsTaskExe();
        addZsTaskExe.setTaskId();
        zsTaskExeMapper.insertZsTaskExe(addZsTaskExe);*/
        //  删除执行任务
        num = zsTaskExeMapper.deleteZsTaskExeByTaskId(String.valueOf(zsTask.getId()));
        insertZsTask(zsTask);

        return num;
    }

    /**
     * 批量删除zs_task
     *
     * @param ids 需要删除的zs_task主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteZsTaskByIds(Long[] ids) {
        String[] stringIds = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            stringIds[i] = String.valueOf(ids[i]);
        }
        zsTaskExeMapper.deleteZsTaskExeByTaskIds(stringIds);
        return zsTaskMapper.deleteZsTaskByIds(ids);
    }

    /**
     * 删除zs_task信息
     *
     * @param id zs_task主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteZsTaskById(Long id) {
        zsTaskExeMapper.deleteZsTaskExeByTaskId(String.valueOf(id));
        return zsTaskMapper.deleteZsTaskById(id);
    }

    public void foo(String id) {

        System.out.printf(id);
    }

}
