package com.ruoyi.common.core.utils.uuid;

/**
 * ID Generator Utility Class
 * 
 * @author ruoyi
 */
public class IdUtils
{
    /**
     * Get random UUID
     * 
     * @return Random UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * Simplified UUID without hyphens
     * 
     * @return Simplified UUID without hyphens
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * Get random UUID using ThreadLocalRandom for better performance
     * 
     * @return Random UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * Simplified UUID without hyphens using ThreadLocalRandom for better performance
     * 
     * @return Simplified UUID without hyphens
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }
}
