package com.ruoyi.job.service;

import java.util.List;
import javax.annotation.PostConstruct;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.core.constant.ScheduleConstants;
import com.ruoyi.common.core.exception.job.TaskException;
import com.ruoyi.job.domain.SysJob;
import com.ruoyi.job.mapper.SysJobMapper;
import com.ruoyi.job.util.CronUtils;
import com.ruoyi.job.util.ScheduleUtils;

/**
 * Scheduled task scheduling information Service layer
 *
 * @author ruoyi
 */
@Service
public class SysJobServiceImpl implements ISysJobService
{
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper jobMapper;

    /**
     * Initialize the timer when the project starts
     * Mainly to prevent manual modification of the database from not being synchronized to the scheduled task processing
     * (Note: You cannot manually modify the database ID and task group name, otherwise it will cause dirty data)
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException
    {
        scheduler.clear();
        List<SysJob> jobList = jobMapper.selectJobAll();
        for (SysJob job : jobList)
        {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }

    /**
     * Get the scheduled task list of the quartz scheduler
     *
     * @param job Scheduling information
     * @return Scheduling task collection
     */
    @Override
    public List<SysJob> selectJobList(SysJob job)
    {
        return jobMapper.selectJobList(job);
    }

    /**
     * Query scheduling information by scheduling task ID
     *
     * @param jobId Scheduling task ID
     * @return Scheduling task object information
     */
    @Override
    public SysJob selectJobById(Long jobId)
    {
        return jobMapper.selectJobById(jobId);
    }

    /**
     * Pause task
     *
     * @param job Scheduling information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int pauseJob(SysJob job) throws SchedulerException
    {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.updateJob(job);
        if (rows > 0)
        {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * Resume task
     *
     * @param job Scheduling information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resumeJob(SysJob job) throws SchedulerException
    {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = jobMapper.updateJob(job);
        if (rows > 0)
        {
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * Delete task. The corresponding trigger will also be deleted
     *
     * @param job Scheduling information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteJob(SysJob job) throws SchedulerException
    {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        int rows = jobMapper.deleteJobById(jobId);
        if (rows > 0)
        {
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * Batch delete scheduling information
     *
     * @param jobIds Task IDs to be deleted
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJobByIds(Long[] jobIds) throws SchedulerException
    {
        for (Long jobId : jobIds)
        {
            SysJob job = jobMapper.selectJobById(jobId);
            deleteJob(job);
        }
    }

    /**
     * Modify task scheduling status
     *
     * @param job Scheduling information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeStatus(SysJob job) throws SchedulerException
    {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status))
        {
            rows = resumeJob(job);
        }
        else if (ScheduleConstants.Status.PAUSE.getValue().equals(status))
        {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * Run task immediately
     *
     * @param job Scheduling information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean run(SysJob job) throws SchedulerException
    {
        boolean result = false;
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        SysJob properties = selectJobById(job.getJobId());
        // Parameters
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey))
        {
            result = true;
            scheduler.triggerJob(jobKey);
        }
        return result;
    }

    /**
     * Add task
     *
     * @param job Scheduling information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertJob(SysJob job) throws SchedulerException, TaskException
    {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.insertJob(job);
        if (rows > 0)
        {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * Update task time expression
     *
     * @param job Scheduling information
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateJob(SysJob job) throws SchedulerException, TaskException
    {
        SysJob properties = selectJobById(job.getJobId());
        int rows = jobMapper.updateJob(job);
        if (rows > 0)
        {
            updateSchedulerJob(job, properties.getJobGroup());
        }
        return rows;
    }

    /**
     * Update task
     *
     * @param job Task object
     * @param jobGroup Task group name
     */
    public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException
    {
        Long jobId = job.getJobId();
        // Determine if it exists
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey))
        {
            // Prevent data issues when creating  Remove first, then perform create operation
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * Verify whether the cron expression is valid
     *
     * @param cronExpression Expression
     * @return Result
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression)
    {
        return CronUtils.isValid(cronExpression);
    }
}
