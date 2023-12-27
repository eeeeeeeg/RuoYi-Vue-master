package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ZsTaskAddParamsMapper;
import com.ruoyi.system.domain.ZsTaskAddParams;
import com.ruoyi.system.service.IZsTaskAddParamsService;

/**
 * taskAddParamsService业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-19
 */
@Service
public class ZsTaskAddParamsServiceImpl implements IZsTaskAddParamsService 
{
    @Autowired
    private ZsTaskAddParamsMapper zsTaskAddParamsMapper;

    /**
     * 查询taskAddParams
     * 
     * @param id taskAddParams主键
     * @return taskAddParams
     */
    @Override
    public ZsTaskAddParams selectZsTaskAddParamsById(Long id)
    {
        return zsTaskAddParamsMapper.selectZsTaskAddParamsById(id);
    }

    /**
     * 查询taskAddParams列表
     * 
     * @param zsTaskAddParams taskAddParams
     * @return taskAddParams
     */
    @Override
    public List<ZsTaskAddParams> selectZsTaskAddParamsList(ZsTaskAddParams zsTaskAddParams)
    {
        return zsTaskAddParamsMapper.selectZsTaskAddParamsList(zsTaskAddParams);
    }

    /**
     * 新增taskAddParams
     * 
     * @param zsTaskAddParams taskAddParams
     * @return 结果
     */
    @Override
    public int insertZsTaskAddParams(ZsTaskAddParams zsTaskAddParams)
    {
        zsTaskAddParams.setCreateTime(DateUtils.getNowDate());
        return zsTaskAddParamsMapper.insertZsTaskAddParams(zsTaskAddParams);
    }

    /**
     * 修改taskAddParams
     * 
     * @param zsTaskAddParams taskAddParams
     * @return 结果
     */
    @Override
    public int updateZsTaskAddParams(ZsTaskAddParams zsTaskAddParams)
    {
        zsTaskAddParams.setUpdateTime(DateUtils.getNowDate());
        return zsTaskAddParamsMapper.updateZsTaskAddParams(zsTaskAddParams);
    }

    /**
     * 批量删除taskAddParams
     * 
     * @param ids 需要删除的taskAddParams主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskAddParamsByIds(Long[] ids)
    {
        return zsTaskAddParamsMapper.deleteZsTaskAddParamsByIds(ids);
    }

    /**
     * 删除taskAddParams信息
     * 
     * @param id taskAddParams主键
     * @return 结果
     */
    @Override
    public int deleteZsTaskAddParamsById(Long id)
    {
        return zsTaskAddParamsMapper.deleteZsTaskAddParamsById(id);
    }
}
