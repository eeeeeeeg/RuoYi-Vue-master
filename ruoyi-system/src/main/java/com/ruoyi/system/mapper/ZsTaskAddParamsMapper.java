package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ZsTaskAddParams;

/**
 * taskAddParamsMapper接口
 * 
 * @author ruoyi
 * @date 2023-12-19
 */
public interface ZsTaskAddParamsMapper 
{
    /**
     * 查询taskAddParams
     * 
     * @param id taskAddParams主键
     * @return taskAddParams
     */
    public ZsTaskAddParams selectZsTaskAddParamsById(Long id);

    /**
     * 查询taskAddParams列表
     * 
     * @param zsTaskAddParams taskAddParams
     * @return taskAddParams集合
     */
    public List<ZsTaskAddParams> selectZsTaskAddParamsList(ZsTaskAddParams zsTaskAddParams);

    /**
     * 新增taskAddParams
     * 
     * @param zsTaskAddParams taskAddParams
     * @return 结果
     */
    public int insertZsTaskAddParams(ZsTaskAddParams zsTaskAddParams);

    /**
     * 修改taskAddParams
     * 
     * @param zsTaskAddParams taskAddParams
     * @return 结果
     */
    public int updateZsTaskAddParams(ZsTaskAddParams zsTaskAddParams);

    /**
     * 删除taskAddParams
     * 
     * @param id taskAddParams主键
     * @return 结果
     */
    public int deleteZsTaskAddParamsById(Long id);

    /**
     * 批量删除taskAddParams
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsTaskAddParamsByIds(Long[] ids);
}
