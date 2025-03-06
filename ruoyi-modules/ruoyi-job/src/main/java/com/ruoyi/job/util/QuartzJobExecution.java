package com.ruoyi.job.util;

import org.quartz.JobExecutionContext;

import com.ruoyi.job.domain.SysJob;

/**
 * Scheduled task processing (allow concurrent execution)
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
