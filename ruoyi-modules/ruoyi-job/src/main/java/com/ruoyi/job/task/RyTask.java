package com.ruoyi.job.task;

import org.springframework.stereotype.Component;
import com.ruoyi.common.core.utils.StringUtils;

/**
 * Scheduled task scheduling test
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("Execute multiple parameter method: String type {}, Boolean type {}, Long type {}, Double type {}, Integer type {}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("Execute method with parameters: " + params);
    }

    public void ryNoParams()
    {
        System.out.println("Execute method without parameters");
    }
}
