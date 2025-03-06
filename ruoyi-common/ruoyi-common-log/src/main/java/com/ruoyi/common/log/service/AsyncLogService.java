package com.ruoyi.common.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.system.api.RemoteLogService;
import com.ruoyi.system.api.domain.SysOperLog;

/**
 * Asynchronous log service call
 * 
 * @author ruoyi
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * Save system log record
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) throws Exception
    {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
