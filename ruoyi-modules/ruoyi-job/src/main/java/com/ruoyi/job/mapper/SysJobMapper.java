package com.ruoyi.job.mapper;

import java.util.List;
import com.ruoyi.job.domain.SysJob;

/**
 * Scheduling Task Information Data Layer
 *
 * @author ruoyi
 */
public interface SysJobMapper
{
    /**
     * Query scheduling task list
     *
     * @param job Scheduling information
     * @return Operation log list
     */
    public List<SysJob> selectJobList(SysJob job);

    /**
     * Query all scheduling tasks
     *
     * @return Scheduling task list
     */
    public List<SysJob> selectJobAll();

    /**
     * Query scheduling task information by ID
     *
     * @param jobId Scheduling ID
     * @return Task information
     */
    public SysJob selectJobById(Long jobId);

    /**
     * Delete scheduling task by ID
     *
     * @param jobId Scheduling ID
     * @return Result
     */
    public int deleteJobById(Long jobId);

    /**
     * Batch delete scheduling tasks
     *
     * @param ids IDs to delete
     * @return Result
     */
    public int deleteJobByIds(Long[] ids);

    /**
     * Update scheduling task
     *
     * @param job Scheduling task
     * @return Result
     */
    public int updateJob(SysJob job);

    /**
     * Create scheduling task
     *
     * @param job Scheduling task
     * @return Result
     */
    public int insertJob(SysJob job);
}
