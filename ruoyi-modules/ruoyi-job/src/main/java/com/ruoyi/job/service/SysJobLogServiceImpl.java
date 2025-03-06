package com.ruoyi.job.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.job.domain.SysJobLog;
import com.ruoyi.job.mapper.SysJobLogMapper;

/**
 * Scheduled Task Log Information Service Layer
 * 
 * @author ruoyi
 */
@Service
public class SysJobLogServiceImpl implements ISysJobLogService
{
    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * Get the scheduled task of the quartz scheduler log
     * 
     * @param jobLog Scheduling log information
     * @return Scheduling task log collection
     */
    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog)
    {
        return jobLogMapper.selectJobLogList(jobLog);
    }

    /**
     * Query scheduling information by task log ID
     * 
     * @param jobLogId Task log ID
     * @return Task log object information
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId)
    {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * Add task log
     * 
     * @param jobLog Scheduling log information
     */
    @Override
    public void addJobLog(SysJobLog jobLog)
    {
        jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * Batch delete scheduling log information
     * 
     * @param logIds Log IDs to delete
     * @return Result
     */
    @Override
    public int deleteJobLogByIds(Long[] logIds)
    {
        return jobLogMapper.deleteJobLogByIds(logIds);
    }

    /**
     * Delete task log
     * 
     * @param jobId Scheduling log ID
     */
    @Override
    public int deleteJobLogById(Long jobId)
    {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * Clear task logs
     */
    @Override
    public void cleanJobLog()
    {
        jobLogMapper.cleanJobLog();
    }
}
