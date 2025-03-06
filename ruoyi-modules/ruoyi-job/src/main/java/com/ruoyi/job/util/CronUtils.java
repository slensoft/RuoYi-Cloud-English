package com.ruoyi.job.util;

import java.text.ParseException;
import java.util.Date;
import org.quartz.CronExpression;

/**
 * cron expression utility class
 * 
 * @author ruoyi
 *
 */
public class CronUtils
{
    /**
     * Returns a boolean value representing the validity of a given Cron expression
     *
     * @param cronExpression Cron expression
     * @return boolean Whether the expression is valid
     */
    public static boolean isValid(String cronExpression)
    {
        return CronExpression.isValidExpression(cronExpression);
    }

    /**
     * Returns a string value indicating the validity of the given Cron expression
     *
     * @param cronExpression Cron expression
     * @return String Returns the expression error description when invalid, returns null if valid
     */
    public static String getInvalidMessage(String cronExpression)
    {
        try
        {
            new CronExpression(cronExpression);
            return null;
        }
        catch (ParseException pe)
        {
            return pe.getMessage();
        }
    }

    /**
     * Returns the next execution time based on the given Cron expression
     *
     * @param cronExpression Cron expression
     * @return Date Next execution time of the Cron expression
     */
    public static Date getNextExecution(String cronExpression)
    {
        try
        {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
