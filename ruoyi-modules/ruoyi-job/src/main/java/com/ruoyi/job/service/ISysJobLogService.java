package com.ruoyi.job.service;

import java.util.List;
import com.ruoyi.job.domain.SysJobLog;

/**
 * Scheduled Task Log Information Service Layer
 * 
 * @author ruoyi
 */
public interface ISysJobLogService
{
    /**
     * Get scheduled tasks from quartz scheduler log
     * 
     * @param jobLog Scheduling log information
     * @return Scheduling task log collection
     */
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog);

    /**
     * Query scheduling information by task log ID
     * 
     * @param jobLogId Task log ID
     * @return Task log object information
     */
    public SysJobLog selectJobLogById(Long jobLogId);

    /**
     * Add task log
     * 
     * @param jobLog Scheduling log information
     */
    public void addJobLog(SysJobLog jobLog);

    /**
     * Batch delete scheduling log information
     * 
     * @param logIds Log IDs to delete
     * @return Result
     */
    public int deleteJobLogByIds(Long[] logIds);

    /**
     * Delete task log
     * 
     * @param jobId Scheduling log ID
     * @return Result
     */
    public int deleteJobLogById(Long jobId);

    /**
     * Clear task logs
     */
    public void cleanJobLog();
}
