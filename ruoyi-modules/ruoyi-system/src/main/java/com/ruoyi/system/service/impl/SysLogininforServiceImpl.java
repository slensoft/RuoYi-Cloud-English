package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.api.domain.SysLogininfor;
import com.ruoyi.system.mapper.SysLogininforMapper;
import com.ruoyi.system.service.ISysLogininforService;

/**
 * System Access Log Information Service Layer Processing
 * 
 * @author ruoyi
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService
{

    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * Add system login log
     * 
     * @param logininfor Access log object
     */
    @Override
    public int insertLogininfor(SysLogininfor logininfor)
    {
        return logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * Query system login log collection
     * 
     * @param logininfor Access log object
     * @return Login record collection
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor)
    {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * Batch delete system login logs
     * 
     * @param infoIds Login log IDs to be deleted
     * @return Result
     */
    @Override
    public int deleteLogininforByIds(Long[] infoIds)
    {
        return logininforMapper.deleteLogininforByIds(infoIds);
    }

    /**
     * Clear system login logs
     */
    @Override
    public void cleanLogininfor()
    {
        logininforMapper.cleanLogininfor();
    }
}
