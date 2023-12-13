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
import com.ruoyi.system.domain.ZsFormTemplate;
import com.ruoyi.system.service.IZsFormTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * formTemplateController
 * 
 * @author ruoyi
 * @date 2023-12-10
 */
@RestController
@RequestMapping("/system/template")
public class ZsFormTemplateController extends BaseController
{
    @Autowired
    private IZsFormTemplateService zsFormTemplateService;

    /**
     * 查询formTemplate列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsFormTemplate zsFormTemplate)
    {
        startPage();
        List<ZsFormTemplate> list = zsFormTemplateService.selectZsFormTemplateList(zsFormTemplate);
        return getDataTable(list);
    }

    /**
     * 导出formTemplate列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:export')")
    @Log(title = "formTemplate", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsFormTemplate zsFormTemplate)
    {
        List<ZsFormTemplate> list = zsFormTemplateService.selectZsFormTemplateList(zsFormTemplate);
        ExcelUtil<ZsFormTemplate> util = new ExcelUtil<ZsFormTemplate>(ZsFormTemplate.class);
        util.exportExcel(response, list, "formTemplate数据");
    }

    /**
     * 获取formTemplate详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:template:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(zsFormTemplateService.selectZsFormTemplateById(id));
    }

    /**
     * 新增formTemplate
     */
    @PreAuthorize("@ss.hasPermi('system:template:add')")
    @Log(title = "formTemplate", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsFormTemplate zsFormTemplate)
    {
        return toAjax(zsFormTemplateService.insertZsFormTemplate(zsFormTemplate));
    }

    /**
     * 修改formTemplate
     */
    @PreAuthorize("@ss.hasPermi('system:template:edit')")
    @Log(title = "formTemplate", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsFormTemplate zsFormTemplate)
    {
        return toAjax(zsFormTemplateService.updateZsFormTemplate(zsFormTemplate));
    }

    /**
     * 删除formTemplate
     */
    @PreAuthorize("@ss.hasPermi('system:template:remove')")
    @Log(title = "formTemplate", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zsFormTemplateService.deleteZsFormTemplateByIds(ids));
    }
}
