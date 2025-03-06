package com.ruoyi.job.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.job.TaskException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.job.domain.SysJob;
import com.ruoyi.job.service.ISysJobService;
import com.ruoyi.job.util.CronUtils;
import com.ruoyi.job.util.ScheduleUtils;

/**
 * Scheduling task information operation processing
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/job")
public class SysJobController extends BaseController
{
    @Autowired
    private ISysJobService jobService;

    /**
     * Query scheduled task list
     */
    @RequiresPermissions("monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob)
    {
        startPage();
        List<SysJob> list = jobService.selectJobList(sysJob);
        return getDataTable(list);
    }

    /**
     * Export scheduled task list
     */
    @RequiresPermissions("monitor:job:export")
    @Log(title = "Scheduled task", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJob sysJob)
    {
        List<SysJob> list = jobService.selectJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        util.exportExcel(response, list, "Scheduled task");
    }

    /**
     * Get scheduled task details
     */
    @RequiresPermissions("monitor:job:query")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return success(jobService.selectJobById(jobId));
    }

    /**
     * Add scheduled task
     */
    @RequiresPermissions("monitor:job:add")
    @Log(title = "Scheduled task", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return error("Failed to add task '" + job.getJobName() + "', invalid Cron expression");
        }
        else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI))
        {
            return error("Failed to add task '" + job.getJobName() + "', target string not allowed 'rmi' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS }))
        {
            return error("Failed to add task '" + job.getJobName() + "', target string not allowed 'ldap(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.HTTP, Constants.HTTPS }))
        {
            return error("Failed to add task '" + job.getJobName() + "', target string not allowed 'http(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR))
        {
            return error("Failed to add task '" + job.getJobName() + "', target string contains violations");
        }
        else if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
        {
            return error("Failed to add task '" + job.getJobName() + "', target string not in white list");
        }
        job.setCreateBy(SecurityUtils.getUsername());
        return toAjax(jobService.insertJob(job));
    }

    /**
     * Modify scheduled task
     */
    @RequiresPermissions("monitor:job:edit")
    @Log(title = "Scheduled task", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJob job) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(job.getCronExpression()))
        {
            return error("Failed to modify task '" + job.getJobName() + "', invalid Cron expression");
        }
        else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI))
        {
            return error("Failed to modify task '" + job.getJobName() + "', target string not allowed 'rmi' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS }))
        {
            return error("Failed to modify task '" + job.getJobName() + "', target string not allowed 'ldap(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[] { Constants.HTTP, Constants.HTTPS }))
        {
            return error("Failed to modify task '" + job.getJobName() + "', target string not allowed 'http(s)' call");
        }
        else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR))
        {
            return error("Failed to modify task '" + job.getJobName() + "', target string contains violations");
        }
        else if (!ScheduleUtils.whiteList(job.getInvokeTarget()))
        {
            return error("Failed to modify task '" + job.getJobName() + "', target string not in white list");
        }
        job.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(jobService.updateJob(job));
    }

    /**
     * Modify scheduled task status
     */
    @RequiresPermissions("monitor:job:changeStatus")
    @Log(title = "Scheduled task", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * Execute scheduled task immediately once
     */
    @RequiresPermissions("monitor:job:changeStatus")
    @Log(title = "Scheduled task", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException
    {
        boolean result = jobService.run(job);
        return result ? success() : error("Task does not exist or has expired!");
    }

    /**
     * Delete scheduled task
     */
    @RequiresPermissions("monitor:job:remove")
    @Log(title = "Scheduled task", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException
    {
        jobService.deleteJobByIds(jobIds);
        return success();
    }
}
