package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.vo.ZsSiteResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ZsSiteMapper;
import com.ruoyi.system.domain.ZsSite;
import com.ruoyi.system.service.IZsSiteService;

/**
 * 站点维护
Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-12-06
 */
@Service
public class ZsSiteServiceImpl implements IZsSiteService 
{
    @Autowired
    private ZsSiteMapper zsSiteMapper;

    /**
     * 查询站点维护

     * 
     * @param id 站点维护
主键
     * @return 站点维护

     */
    @Override
    public ZsSite selectZsSiteById(Long id)
    {
        return zsSiteMapper.selectZsSiteById(id);
    }

    /**
     * 查询站点维护
列表
     * 
     * @param zsSite 站点维护

     * @return 站点维护

     */
    @Override
    public List<ZsSiteResultVo> selectZsSiteList(ZsSite zsSite)
    {
        return zsSiteMapper.selectZsSiteList(zsSite);
    }

    /**
     * 新增站点维护

     * 
     * @param zsSite 站点维护

     * @return 结果
     */
    @Override
    public int insertZsSite(ZsSite zsSite,Long userId)
    {
        zsSite.setCreateUser(userId);
        zsSite.setCreateTime(DateUtils.getNowDate());
        return zsSiteMapper.insertZsSite(zsSite);
    }

    /**
     * 修改站点维护

     * 
     * @param zsSite 站点维护

     * @return 结果
     */
    @Override
    public int updateZsSite(ZsSite zsSite,Long userId)
    {
        zsSite.setUpdateUser(userId);
        zsSite.setUpdateTime(DateUtils.getNowDate());
        return zsSiteMapper.updateZsSite(zsSite);
    }

    /**
     * 批量删除站点维护

     * 
     * @param ids 需要删除的站点维护
主键
     * @return 结果
     */
    @Override
    public int deleteZsSiteByIds(Long[] ids)
    {
        return zsSiteMapper.deleteZsSiteByIds(ids);
    }

    /**
     * 删除站点维护
信息
     * 
     * @param id 站点维护
主键
     * @return 结果
     */
    @Override
    public int deleteZsSiteById(Long id)
    {
        return zsSiteMapper.deleteZsSiteById(id);
    }
}
