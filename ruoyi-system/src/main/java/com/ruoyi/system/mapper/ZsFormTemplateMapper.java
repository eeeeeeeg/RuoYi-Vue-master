package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ZsFormTemplate;

/**
 * formTemplateMapper接口
 * 
 * @author ruoyi
 * @date 2023-12-10
 */
public interface ZsFormTemplateMapper 
{
    /**
     * 查询formTemplate
     * 
     * @param id formTemplate主键
     * @return formTemplate
     */
    public ZsFormTemplate selectZsFormTemplateById(Long id);

    /**
     * 查询formTemplate列表
     * 
     * @param zsFormTemplate formTemplate
     * @return formTemplate集合
     */
    public List<ZsFormTemplate> selectZsFormTemplateList(ZsFormTemplate zsFormTemplate);

    /**
     * 新增formTemplate
     * 
     * @param zsFormTemplate formTemplate
     * @return 结果
     */
    public int insertZsFormTemplate(ZsFormTemplate zsFormTemplate);

    /**
     * 修改formTemplate
     * 
     * @param zsFormTemplate formTemplate
     * @return 结果
     */
    public int updateZsFormTemplate(ZsFormTemplate zsFormTemplate);

    /**
     * 删除formTemplate
     * 
     * @param id formTemplate主键
     * @return 结果
     */
    public int deleteZsFormTemplateById(Long id);

    /**
     * 批量删除formTemplate
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsFormTemplateByIds(Long[] ids);
}
