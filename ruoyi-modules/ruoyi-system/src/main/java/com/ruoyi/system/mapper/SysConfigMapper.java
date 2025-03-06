package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysConfig;

/**
 * Parameter Configuration Data Layer
 * 
 * @author ruoyi
 */
public interface SysConfigMapper
{
    /**
     * Query parameter configuration information
     * 
     * @param config Parameter configuration information
     * @return Parameter configuration information
     */
    public SysConfig selectConfig(SysConfig config);

    /**
     * Query configuration by ID
     * 
     * @param configId Parameter ID
     * @return Parameter configuration information
     */
    public SysConfig selectConfigById(Long configId);

    /**
     * Query parameter configuration list
     * 
     * @param config Parameter configuration information
     * @return Parameter configuration collection
     */
    public List<SysConfig> selectConfigList(SysConfig config);

    /**
     * Query parameter configuration information by key name
     * 
     * @param configKey Parameter key name
     * @return Parameter configuration information
     */
    public SysConfig checkConfigKeyUnique(String configKey);

    /**
     * Add parameter configuration
     * 
     * @param config Parameter configuration information
     * @return Result
     */
    public int insertConfig(SysConfig config);

    /**
     * Modify parameter configuration
     * 
     * @param config Parameter configuration information
     * @return Result
     */
    public int updateConfig(SysConfig config);

    /**
     * Delete parameter configuration
     * 
     * @param configId Parameter ID
     * @return Result
     */
    public int deleteConfigById(Long configId);

    /**
     * Batch delete parameter information
     * 
     * @param configIds Parameter IDs to be deleted
     * @return Result
     */
    public int deleteConfigByIds(Long[] configIds);
}