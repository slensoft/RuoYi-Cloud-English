package com.ruoyi.common.security.utils;

import java.util.Collection;
import java.util.List;
import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.domain.SysDictData;

/**
 * Dictionary utility class
 * 
 * @author ruoyi
 */
public class DictUtils
{
    /**
     * Set dictionary cache
     * 
     * @param key Parameter key
     * @param dictDatas Dictionary data list
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisService.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * Get dictionary cache
     * 
     * @param key Parameter key
     * @return dictDatas Dictionary data list
     */
    public static List<SysDictData> getDictCache(String key)
    {
        JSONArray arrayCache = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(arrayCache))
        {
            return arrayCache.toList(SysDictData.class);
        }
        return null;
    }

    /**
     * Delete specified dictionary cache
     * 
     * @param key Dictionary key
     */
    public static void removeDictCache(String key)
    {
        SpringUtils.getBean(RedisService.class).deleteObject(getCacheKey(key));
    }

    /**
     * Clear dictionary cache
     */
    public static void clearDictCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisService.class).keys(CacheConstants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisService.class).deleteObject(keys);
    }

    /**
     * Set cache key
     * 
     * @param configKey Parameter key
     * @return Cache key
     */
    public static String getCacheKey(String configKey)
    {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }
}
