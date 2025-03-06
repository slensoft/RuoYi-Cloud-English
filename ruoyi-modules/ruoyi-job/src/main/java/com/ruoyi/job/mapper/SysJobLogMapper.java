package com.ruoyi.job.mapper;

import java.util.List;
import com.ruoyi.job.domain.SysJobLog;

/**
 * Scheduling task log information Data layer
 * 
 * @author ruoyi
 */
public interface SysJobLogMapper
{
    /**
     * Get the scheduled task of the quartz scheduler log
     * 
     * @param jobLog Scheduling log information
     * @return Scheduling task log collection
     */
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog);

    /**
     * Query all scheduling task logs
     *
     * @return Scheduling task log list
     */
    public List<SysJobLog> selectJobLogAll();

    /**
     * Query scheduling information by scheduling task log ID
     * 
     * @param jobLogId Scheduling task log ID
     * @return Scheduling task log object information
     */
    public SysJobLog selectJobLogById(Long jobLogId);

    /**
     * Add task log
     * 
     * @param jobLog Scheduling log information
     * @return result
     */
    public int insertJobLog(SysJobLog jobLog);

    /**
     * Batch delete scheduling log information
     * 
     * @param logIds IDs of data to be deleted
     * @return result
     */
    public int deleteJobLogByIds(Long[] logIds);

    /**
     * Delete task log
     * 
     * @param jobId Scheduling log ID
     * @return result
     */
    public int deleteJobLogById(Long jobId);

    /**
     * Clear task log
     */
    public void cleanJobLog();
}
