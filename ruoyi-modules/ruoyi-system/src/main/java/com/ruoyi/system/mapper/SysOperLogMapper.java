package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.api.domain.SysOperLog;

/**
 * Operation Log Data Layer
 * 
 * @author ruoyi
 */
public interface SysOperLogMapper
{
    /**
     * Add operation log
     * 
     * @param operLog Operation log object
     */
    public int insertOperlog(SysOperLog operLog);

    /**
     * Query system operation log collection
     * 
     * @param operLog Operation log object
     * @return Operation log collection
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * Batch delete system operation logs
     * 
     * @param operIds Operation log IDs to be deleted
     * @return Result
     */
    public int deleteOperLogByIds(Long[] operIds);

    /**
     * Query operation log details
     * 
     * @param operId Operation ID
     * @return Operation log object
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * Clear operation log
     */
    public void cleanOperLog();
}
