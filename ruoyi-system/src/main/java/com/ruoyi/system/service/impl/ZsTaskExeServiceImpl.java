package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ZsTaskExeMapper;
import com.ruoyi.system.domain.ZsTaskExe;
import com.ruoyi.system.service.IZsTaskExeService;

/**
 * taskExeService业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
@Service
public class ZsTaskExeServiceImpl implements IZsTaskExeService 
{
    @Autowired
    private ZsTaskExeMapper zsTaskExeMapper;

    /**
     * 查询taskExe
     * 
     * @param id taskExe主键
     * @return taskExe
     */
    @Override
    public ZsTaskExe selectZsTaskExeById(Long id)
    {
        return zsTaskExeMapper.selectZsTaskExeById(id);
    }

    /**
     * 查询taskExe列表
     * 
     * @param zsTaskExe taskExe
     * @return taskExe
     */
    @Override
    public List<ZsTaskExe> selectZsTaskExeList(ZsTaskExe zsTaskExe)
    {
        return zsTaskExeMapper.selectZsTaskExeList(zsTaskExe);
    }

    /**
     * 新增taskExe
     * 
     * @param zsTaskExe taskExe
     * @return 结果
     */
    @Override
    public int insertZsTaskExe(ZsTaskExe zsTaskExe)
    {
        return zsTaskExeMapper.insertZsTaskExe(zsTaskExe);
    }

    /**
     * 修改taskExe
     * 
     * @param zsTaskExe taskExe
     * @return 结果
     */
    @Override
    public int updateZsTaskExe(ZsTaskExe zsTaskExe)
    {
        return zsTaskExeMapper.updateZsTaskExe(zsTaskExe);
    }

    /**
     * 批量删除taskExe
     * 
     * @param ids 需要删除的taskExe主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskExeByIds(Long[] ids)
    {
        return zsTaskExeMapper.deleteZsTaskExeByIds(ids);
    }

    /**
     * 删除taskExe信息
     * 
     * @param id taskExe主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskExeById(Long id)
    {
        return zsTaskExeMapper.deleteZsTaskExeById(id);
    }
}
