package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateTransUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.AllTaskList;
import com.ruoyi.system.domain.TaskExeEditInfo;
import com.ruoyi.common.core.domain.model.ZsTask;
import com.ruoyi.system.domain.ZsTaskExe;
import com.ruoyi.system.mapper.ZsTaskExeMapper;
import com.ruoyi.system.mapper.ZsTaskMapper;
import com.ruoyi.system.service.IZsTaskExeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * taskExeService业务层处理
 *
 * @author ruoyi
 * @date 2023-12-12
 */
@Service
public class ZsTaskExeServiceImpl implements IZsTaskExeService {
    @Autowired
    private ZsTaskExeMapper zsTaskExeMapper;

    @Autowired
    private ZsTaskMapper zsTaskMapper;

    /**
     * 查询taskExe
     *
     * @param id taskExe主键
     * @return taskExe
     */
    @Override
    public ZsTaskExe selectZsTaskExeById(Long id) {
        return zsTaskExeMapper.selectZsTaskExeById(id);
    }

    /**
     * 查询taskExe列表
     *
     * @param zsTaskExe taskExe
     * @return taskExe
     */
    @Override
    public List<ZsTaskExe> selectZsTaskExeList(ZsTaskExe zsTaskExe) {

        return zsTaskExeMapper.selectZsTaskExeList(zsTaskExe);
    }

    @Override
    public List<AllTaskList> allTaskList() {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        return zsTaskExeMapper.allTaskList(userId);
    }

    /**
     * 新增taskExe
     *
     * @param zsTaskExe taskExe
     * @return 结果
     */
    @Override
    public int insertZsTaskExe(ZsTaskExe zsTaskExe) {
        return zsTaskExeMapper.insertZsTaskExe(zsTaskExe);
    }

    /**
     * 修改taskExe
     *
     * @param zsTaskExe taskExe
     * @return 结果
     */
    @Override
    public int updateZsTaskExe(ZsTaskExe zsTaskExe) {
        return zsTaskExeMapper.updateZsTaskExe(zsTaskExe);
    }

    @Override
    @Transactional
    public int editExeAllTaskInfo(TaskExeEditInfo taskExeEditInfo) throws ParseException {
        if (taskExeEditInfo.getTaskStatus().equals("3")) {
            if (Integer.valueOf(taskExeEditInfo.getTaskType()) > 1 || Integer.valueOf(taskExeEditInfo.getTaskType()) < 6) {
                for (int i = 1; i < Integer.valueOf(taskExeEditInfo.getTaskType()); i++) {
                    ZsTask zsTask = new ZsTask();
                    zsTask.setStatus(taskExeEditInfo.getTaskStatus());
                    zsTask.setTaskType(String.valueOf(i));
                    zsTask.setTaskRang(DateTransUtils.getDateValue(DateTransUtils.dateToString(taskExeEditInfo.getEndTime()), i));
                    zsTask.setSiteId(taskExeEditInfo.getSiteId());
                    zsTaskMapper.updateZsTaskStatus(zsTask);
                }
            }
        }
        ZsTask zsTask = new ZsTask();
        zsTask.setId(taskExeEditInfo.getTaskId());
        zsTask.setStatus(taskExeEditInfo.getTaskStatus());
        if(taskExeEditInfo.getTaskStatus().equals("3")){
            ZsTaskExe zsTaskExeResp = zsTaskExeMapper.selectZsTaskExeById(taskExeEditInfo.getExeId());
            zsTask.setTaskBeginTime(zsTaskExeResp.getBeginTime());
            zsTask.setTaskEndTime(taskExeEditInfo.getEndTime());
            zsTask.setExecutor(zsTaskExeResp.getExecutor());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 计算时间差，单位为毫秒
            long timeDifferenceMillis = dateFormat.parse(DateTransUtils.dateToString(taskExeEditInfo.getEndTime())).getTime() - dateFormat.parse(DateTransUtils.dateToString(taskExeEditInfo.getBeginTime())).getTime();
            // 将毫秒转换为秒
            long secondsDifference = timeDifferenceMillis / 1000;
            zsTask.setPatrolDuration(secondsDifference);
            zsTask.setFormResult(zsTaskExeResp.getFormResult());
        }

        zsTaskMapper.updateZsTask(zsTask);
        ZsTaskExe zsTaskExe = new ZsTaskExe();
        zsTaskExe.setId(taskExeEditInfo.getExeId());
        zsTaskExe.setBeginTime(taskExeEditInfo.getBeginTime());
        zsTaskExe.setEndTime(taskExeEditInfo.getEndTime());
        zsTaskExe.setFormResult(taskExeEditInfo.getFormResult());
        zsTaskExe.setFormStatus(taskExeEditInfo.getFormStatus());
        return zsTaskExeMapper.updateZsTaskExe(zsTaskExe);
    }


    @Override
    @Transactional
    public int editExeInfo(AllTaskList allTaskList) {
        //  先改任务的表数据   再改任务表的状态
        ZsTask zsTask = new ZsTask();
        zsTask.setId(allTaskList.getTaskId());
        zsTask.setStatus(allTaskList.getTaskStatus());
        zsTaskMapper.updateZsTask(zsTask);
        return zsTaskExeMapper.editExeInfo(allTaskList);
    }

    /**
     * 批量删除taskExe
     *
     * @param ids 需要删除的taskExe主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskExeByIds(Long[] ids) {
        return zsTaskExeMapper.deleteZsTaskExeByIds(ids);
    }

    /**
     * 删除taskExe信息
     *
     * @param id taskExe主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskExeById(Long id) {
        return zsTaskExeMapper.deleteZsTaskExeById(id);
    }
}
