package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.api.domain.SysLogininfor;

/**
 * System Access Log Information Data Layer
 * 
 * @author ruoyi
 */
public interface SysLogininforMapper
{
    /**
     * Add system login log
     * 
     * @param logininfor Access log object
     */
    public int insertLogininfor(SysLogininfor logininfor);

    /**
     * Query system login log collection
     * 
     * @param logininfor Access log object
     * @return Login record collection
     */
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     * Batch delete system login logs
     * 
     * @param infoIds Login log IDs to be deleted
     * @return Result
     */
    public int deleteLogininforByIds(Long[] infoIds);

    /**
     * Clear system login logs
     * 
     * @return Result
     */
    public int cleanLogininfor();
}
