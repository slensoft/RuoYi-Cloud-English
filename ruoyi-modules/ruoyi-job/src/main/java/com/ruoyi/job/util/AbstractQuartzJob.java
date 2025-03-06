package com.ruoyi.job.util;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.core.constant.ScheduleConstants;
import com.ruoyi.common.core.utils.ExceptionUtil;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.job.domain.SysJob;
import com.ruoyi.job.domain.SysJobLog;
import com.ruoyi.job.service.ISysJobLogService;

/**
 * Abstract quartz call
 *
 * @author ruoyi
 */
public abstract class AbstractQuartzJob implements Job
{
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * Thread local variable
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        SysJob sysJob = new SysJob();
        BeanUtils.copyBeanProp(sysJob, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try
        {
            before(context, sysJob);
            if (sysJob != null)
            {
                doExecute(context, sysJob);
            }
            after(context, sysJob, null);
        }
        catch (Exception e)
        {
            log.error("Task execution exception  - :", e);
            after(context, sysJob, e);
        }
    }

    /**
     * Before execution
     *
     * @param context Job execution context object
     * @param sysJob System scheduled task
     */
    protected void before(JobExecutionContext context, SysJob sysJob)
    {
        threadLocal.set(new Date());
    }

    /**
     * After execution
     *
     * @param context Job execution context object
     * @param sysJob System scheduled task
     * @param e Exception during execution
     */
    protected void after(JobExecutionContext context, SysJob sysJob, Exception e)
    {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobName(sysJob.getJobName());
        sysJobLog.setJobGroup(sysJob.getJobGroup());
        sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
        sysJobLog.setStartTime(startTime);
        sysJobLog.setStopTime(new Date());
        long runMs = sysJobLog.getStopTime().getTime() - sysJobLog.getStartTime().getTime();
        sysJobLog.setJobMessage(sysJobLog.getJobName() + " Total time: " + runMs + " milliseconds");
        if (e != null)
        {
            sysJobLog.setStatus("1");
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            sysJobLog.setExceptionInfo(errorMsg);
        }
        else
        {
            sysJobLog.setStatus("0");
        }

        // Write to the database
        SpringUtils.getBean(ISysJobLogService.class).addJobLog(sysJobLog);
    }

    /**
     * Execute method, overridden by subclasses
     *
     * @param context Job execution context object
     * @param sysJob System scheduled task
     * @throws Exception Exception during execution
     */
    protected abstract void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception;
}
