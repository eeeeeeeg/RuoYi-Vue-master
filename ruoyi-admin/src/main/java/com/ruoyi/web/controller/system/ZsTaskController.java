package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.ZsTask;
import com.ruoyi.system.service.IZsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * zs_taskController
 *
 * @author ruoyi
 * @date 2023-12-08
 */
@RestController
@RequestMapping("/system/task")
public class ZsTaskController extends BaseController {
    @Autowired
    private IZsTaskService zsTaskService;


    /**
     * 查询zs_task列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsTask zsTask) {
        startPage();
        List<ZsTask> list = zsTaskService.selectZsTaskList(zsTask);
        return getDataTable(list);
    }

    /**
     * 导出zs_task列表
     */
    @PreAuthorize("@ss.hasPermi('system:task:export')")
    @Log(title = "zs_task", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsTask zsTask) {
        List<ZsTask> list = zsTaskService.selectZsTaskList(zsTask);
        ExcelUtil<ZsTask> util = new ExcelUtil<ZsTask>(ZsTask.class);
        util.exportExcel(response, list, "zs_task数据");
    }

    /**
     * 获取zs_task详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(zsTaskService.selectZsTaskById(id));
    }

    /**
     * 新增zs_task
     */
    @PreAuthorize("@ss.hasPermi('system:task:add')")
    @Log(title = "zs_task", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsTask zsTask) {
        return toAjax(zsTaskService.insertZsTask(zsTask));
    }

    /**
     * 修改zs_task
     */
    @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "zs_task", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsTask zsTask) {
        return toAjax(zsTaskService.updateZsTask(zsTask, getUserId().toString()));
    }

    /**
     * 删除zs_task
     */
    @PreAuthorize("@ss.hasPermi('system:task:remove')")
    @Log(title = "zs_task", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(zsTaskService.deleteZsTaskByIds(ids));
    }
}
