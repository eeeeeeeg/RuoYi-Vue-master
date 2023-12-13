package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ZsSite;
import com.ruoyi.system.domain.vo.ZsSiteResultVo;
import com.ruoyi.system.service.IZsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 站点维护
 * Controller
 *
 * @author ruoyi
 * @date 2023-12-06
 */
@RestController
@RequestMapping("/system/site")
public class ZsSiteController extends BaseController {
    @Autowired
    private IZsSiteService zsSiteService;

    /**
     * 查询站点维护
     * 列表
     */
    @PreAuthorize("@ss.hasPermi('system:site:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsSite zsSite) {
        startPage();
        List<ZsSiteResultVo> list = zsSiteService.selectZsSiteList(zsSite);
        return getDataTable(list);
    }

    /**
     * 导出站点维护
     * 列表
     */
    @PreAuthorize("@ss.hasPermi('system:site:export')")
    @Log(title = "站点维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsSite zsSite) {
        List<ZsSiteResultVo> list = zsSiteService.selectZsSiteList(zsSite);
        ExcelUtil<ZsSiteResultVo> util = new ExcelUtil<ZsSiteResultVo>(ZsSiteResultVo.class);
        util.exportExcel(response, list, "站点维护数据");
    }

    /**
     * 获取站点维护
     * 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:site:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(zsSiteService.selectZsSiteById(id));
    }


    /**
     * 新增站点维护
     */
    @PreAuthorize("@ss.hasPermi('system:site:add')")
    @Log(title = "站点维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsSite zsSite) {
        return toAjax(zsSiteService.insertZsSite(zsSite, getUserId()));
    }

    /**
     * 修改站点维护
     */
    @PreAuthorize("@ss.hasPermi('system:site:edit')")
    @Log(title = "站点维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsSite zsSite) {
        return toAjax(zsSiteService.updateZsSite(zsSite, getUserId()));
    }

    /**
     * 删除站点维护
     */
    @PreAuthorize("@ss.hasPermi('system:site:remove')")
    @Log(title = "站点维护", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(zsSiteService.deleteZsSiteByIds(ids));
    }
}
