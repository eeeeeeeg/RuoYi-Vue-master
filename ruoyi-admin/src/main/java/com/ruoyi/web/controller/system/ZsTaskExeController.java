package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.AllTaskList;
import com.ruoyi.system.domain.TaskExeEditInfo;
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
import com.ruoyi.system.domain.ZsTaskExe;
import com.ruoyi.system.service.IZsTaskExeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * taskExeController
 * 
 * @author ruoyi
 * @date 2023-12-12
 */
@RestController
@RequestMapping("/system/taskExe")
public class ZsTaskExeController extends BaseController
{
    @Autowired
    private IZsTaskExeService zsTaskExeService;

    /**
     * 查询taskExe列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskExe:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsTaskExe zsTaskExe)
    {
        startPage();
        List<ZsTaskExe> list = zsTaskExeService.selectZsTaskExeList(zsTaskExe);
        return getDataTable(list);
    }


    /**
     * 查询关联taskExe列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskExe:list')")
    @GetMapping("/allTaskList")
    public AjaxResult allTaskList()
    {
        List <AllTaskList> list = zsTaskExeService.allTaskList();
        return AjaxResult.success(list);
    }

    /**
     * 导出taskExe列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskExe:export')")
    @Log(title = "taskExe", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsTaskExe zsTaskExe)
    {
        List<ZsTaskExe> list = zsTaskExeService.selectZsTaskExeList(zsTaskExe);
        ExcelUtil<ZsTaskExe> util = new ExcelUtil<ZsTaskExe>(ZsTaskExe.class);
        util.exportExcel(response, list, "taskExe数据");
    }

    /**
     * 获取taskExe详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskExe:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(zsTaskExeService.selectZsTaskExeById(id));
    }

    /**
     * 新增taskExe
     */
    @PreAuthorize("@ss.hasPermi('system:taskExe:add')")
    @Log(title = "taskExe", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsTaskExe zsTaskExe)
    {
        return toAjax(zsTaskExeService.insertZsTaskExe(zsTaskExe));
    }

    /**
     * 修改taskExe
     */
    @PreAuthorize("@ss.hasPermi('system:taskExe:edit')")
    @Log(title = "taskExe", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsTaskExe zsTaskExe)
    {
        return toAjax(zsTaskExeService.updateZsTaskExe(zsTaskExe));
    }


    @PreAuthorize("@ss.hasPermi('system:taskExe:edit')")
    @Log(title = "taskExe", businessType = BusinessType.UPDATE)
    @PostMapping("/editExeAllTaskInfo")
    public AjaxResult editExeAllTaskInfo(@RequestBody TaskExeEditInfo taskExeEditInfo)
    {
        return toAjax(zsTaskExeService.editExeAllTaskInfo(taskExeEditInfo));
    }


    @PreAuthorize("@ss.hasPermi('system:taskExe:edit')")
    @Log(title = "taskExe", businessType = BusinessType.UPDATE)
    @PostMapping("/editExeInfo")
    public AjaxResult editExeInfo(@RequestBody AllTaskList allTaskList)
    {
        return toAjax(zsTaskExeService.editExeInfo(allTaskList));
    }

    /**
     * 删除taskExe
     */
    @PreAuthorize("@ss.hasPermi('system:taskExe:remove')")
    @Log(title = "taskExe", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zsTaskExeService.deleteZsTaskExeByIds(ids));
    }
}
