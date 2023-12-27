package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ZsTaskAddParams;
import com.ruoyi.system.service.IZsTaskAddParamsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskAddParamsController
 * 
 * @author ruoyi
 * @date 2023-12-19
 */
@RestController
@RequestMapping("/system/taskAddParams")
public class ZsTaskAddParamsController extends BaseController
{
    @Autowired
    private IZsTaskAddParamsService zsTaskAddParamsService;

    /**
     * 查询taskAddParams列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskAddParams:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsTaskAddParams zsTaskAddParams)
    {
        startPage();
        List<ZsTaskAddParams> list = zsTaskAddParamsService.selectZsTaskAddParamsList(zsTaskAddParams);
        return getDataTable(list);
    }

    /**
     * 导出taskAddParams列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskAddParams:export')")
    @Log(title = "taskAddParams", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsTaskAddParams zsTaskAddParams)
    {
        List<ZsTaskAddParams> list = zsTaskAddParamsService.selectZsTaskAddParamsList(zsTaskAddParams);
        ExcelUtil<ZsTaskAddParams> util = new ExcelUtil<ZsTaskAddParams>(ZsTaskAddParams.class);
        util.exportExcel(response, list, "taskAddParams数据");
    }

    /**
     * 获取taskAddParams详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskAddParams:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(zsTaskAddParamsService.selectZsTaskAddParamsById(id));
    }

    /**
     * 新增taskAddParams
     */
    @PreAuthorize("@ss.hasPermi('system:taskAddParams:add')")
    @Log(title = "taskAddParams", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsTaskAddParams zsTaskAddParams)
    {
        return toAjax(zsTaskAddParamsService.insertZsTaskAddParams(zsTaskAddParams));
    }

    /**
     * 修改taskAddParams
     */
    @PreAuthorize("@ss.hasPermi('system:taskAddParams:edit')")
    @Log(title = "taskAddParams", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsTaskAddParams zsTaskAddParams)
    {
        return toAjax(zsTaskAddParamsService.updateZsTaskAddParams(zsTaskAddParams));
    }

    /**
     * 删除taskAddParams
     */
    @PreAuthorize("@ss.hasPermi('system:taskAddParams:remove')")
    @Log(title = "taskAddParams", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zsTaskAddParamsService.deleteZsTaskAddParamsByIds(ids));
    }
}
