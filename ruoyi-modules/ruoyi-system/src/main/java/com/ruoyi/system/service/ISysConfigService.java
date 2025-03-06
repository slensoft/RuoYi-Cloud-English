package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysConfig;

/**
 * Parameter Configuration Service Layer
 * 
 * @author ruoyi
 */
public interface ISysConfigService
{
    /**
     * Query parameter configuration information
     * 
     * @param configId Parameter configuration ID
     * @return Parameter configuration information
     */
    public SysConfig selectConfigById(Long configId);

    /**
     * Query parameter configuration information by key name
     * 
     * @param configKey Parameter key name
     * @return Parameter key value
     */
    public String selectConfigByKey(String configKey);

    /**
     * Query parameter configuration list
     * 
     * @param config Parameter configuration information
     * @return Parameter configuration collection
     */
    public List<SysConfig> selectConfigList(SysConfig config);

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
     * Batch delete parameter information
     * 
     * @param configIds Parameter IDs to be deleted
     */
    public void deleteConfigByIds(Long[] configIds);

    /**
     * Load parameter cache data
     */
    public void loadingConfigCache();

    /**
     * Clear parameter cache data
     */
    public void clearConfigCache();

    /**
     * Reset parameter cache data
     */
    public void resetConfigCache();

    /**
     * Check if parameter key name is unique
     * 
     * @param config Parameter information
     * @return Result
     */
    public boolean checkConfigKeyUnique(SysConfig config);
}
