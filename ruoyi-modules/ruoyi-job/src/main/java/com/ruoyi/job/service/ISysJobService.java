package com.ruoyi.job.service;

import java.util.List;
import org.quartz.SchedulerException;
import com.ruoyi.common.core.exception.job.TaskException;
import com.ruoyi.job.domain.SysJob;

/**
 * Scheduled Task Scheduling Information Service Layer
 * 
 * @author ruoyi
 */
public interface ISysJobService
{
    /**
     * Get the scheduled task of the quartz scheduler
     * 
     * @param job Scheduling information
     * @return Scheduling task collection
     */
    public List<SysJob> selectJobList(SysJob job);

    /**
     * Query scheduling information by task ID
     * 
     * @param jobId Task ID
     * @return Task scheduling object information
     */
    public SysJob selectJobById(Long jobId);

    /**
     * Pause task
     * 
     * @param job Scheduling information
     * @return Result
     */
    public int pauseJob(SysJob job) throws SchedulerException;

    /**
     * Resume task
     * 
     * @param job Scheduling information
     * @return Result
     */
    public int resumeJob(SysJob job) throws SchedulerException;

    /**
     * Delete task and its corresponding trigger
     * 
     * @param job Scheduling information
     * @return Result
     */
    public int deleteJob(SysJob job) throws SchedulerException;

    /**
     * Batch delete scheduling information
     * 
     * @param jobIds Task IDs to delete
     * @return Result
     */
    public void deleteJobByIds(Long[] jobIds) throws SchedulerException;

    /**
     * Modify task scheduling status
     * 
     * @param job Scheduling information
     * @return Result
     */
    public int changeStatus(SysJob job) throws SchedulerException;

    /**
     * Run task immediately
     * 
     * @param job Scheduling information
     * @return Result
     */
    public boolean run(SysJob job) throws SchedulerException;

    /**
     * Add new task
     * 
     * @param job Scheduling information
     * @return Result
     */
    public int insertJob(SysJob job) throws SchedulerException, TaskException;

    /**
     * Update task
     * 
     * @param job Scheduling information
     * @return Result
     */
    public int updateJob(SysJob job) throws SchedulerException, TaskException;

    /**
     * Validate if cron expression is valid
     * 
     * @param cronExpression Expression
     * @return Result
     */
    public boolean checkCronExpressionIsValid(String cronExpression);
}
