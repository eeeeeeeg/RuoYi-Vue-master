package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ZsFormTemplateMapper;
import com.ruoyi.system.domain.ZsFormTemplate;
import com.ruoyi.system.service.IZsFormTemplateService;

/**
 * formTemplateService业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-10
 */
@Service
public class ZsFormTemplateServiceImpl implements IZsFormTemplateService 
{
    @Autowired
    private ZsFormTemplateMapper zsFormTemplateMapper;

    /**
     * 查询formTemplate
     * 
     * @param id formTemplate主键
     * @return formTemplate
     */
    @Override
    public ZsFormTemplate selectZsFormTemplateById(Long id)
    {
        return zsFormTemplateMapper.selectZsFormTemplateById(id);
    }

    /**
     * 查询formTemplate列表
     * 
     * @param zsFormTemplate formTemplate
     * @return formTemplate
     */
    @Override
    public List<ZsFormTemplate> selectZsFormTemplateList(ZsFormTemplate zsFormTemplate)
    {
        return zsFormTemplateMapper.selectZsFormTemplateList(zsFormTemplate);
    }

    /**
     * 新增formTemplate
     * 
     * @param zsFormTemplate formTemplate
     * @return 结果
     */
    @Override
    public int insertZsFormTemplate(ZsFormTemplate zsFormTemplate)
    {
        zsFormTemplate.setCreateTime(DateUtils.getNowDate());
        return zsFormTemplateMapper.insertZsFormTemplate(zsFormTemplate);
    }

    /**
     * 修改formTemplate
     * 
     * @param zsFormTemplate formTemplate
     * @return 结果
     */
    @Override
    public int updateZsFormTemplate(ZsFormTemplate zsFormTemplate)
    {
        return zsFormTemplateMapper.updateZsFormTemplate(zsFormTemplate);
    }

    /**
     * 批量删除formTemplate
     * 
     * @param ids 需要删除的formTemplate主键
     * @return 结果
     */
    @Override
    public int deleteZsFormTemplateByIds(Long[] ids)
    {
        return zsFormTemplateMapper.deleteZsFormTemplateByIds(ids);
    }

    /**
     * 删除formTemplate信息
     * 
     * @param id formTemplate主键
     * @return 结果
     */
    @Override
    public int deleteZsFormTemplateById(Long id)
    {
        return zsFormTemplateMapper.deleteZsFormTemplateById(id);
    }
}
