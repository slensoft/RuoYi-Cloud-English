package com.ruoyi.job.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

import com.ruoyi.job.domain.SysJob;

/**
 * Scheduled task processing (prohibit concurrent execution)
 * 
 * @author ruoyi
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
