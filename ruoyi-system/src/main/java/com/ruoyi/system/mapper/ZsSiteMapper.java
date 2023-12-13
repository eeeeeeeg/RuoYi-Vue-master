package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ZsSite;
import com.ruoyi.system.domain.vo.ZsSiteResultVo;

/**
 * 站点维护
Mapper接口
 * 
 * @author ruoyi
 * @date 2023-12-06
 */
public interface ZsSiteMapper 
{
    /**
     * 查询站点维护

     * 
     * @param id 站点维护
主键
     * @return 站点维护

     */
    public ZsSite selectZsSiteById(Long id);

    /**
     * 查询站点维护
列表
     * 
     * @param zsSite 站点维护

     * @return 站点维护
     */
    public List<ZsSiteResultVo> selectZsSiteList(ZsSite zsSite);

    

    /**
     * 新增站点维护

     * 
     * @param zsSite 站点维护

     * @return 结果
     */
    public int insertZsSite(ZsSite zsSite);

    /**
     * 修改站点维护

     * 
     * @param zsSite 站点维护

     * @return 结果
     */
    public int updateZsSite(ZsSite zsSite);

    /**
     * 删除站点维护

     * 
     * @param id 站点维护
主键
     * @return 结果
     */
    public int deleteZsSiteById(Long id);

    /**
     * 批量删除站点维护

     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsSiteByIds(Long[] ids);
}
