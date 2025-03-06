package com.ruoyi.common.core.utils.sql;

import com.ruoyi.common.core.exception.UtilException;
import com.ruoyi.common.core.utils.StringUtils;

/**
 * SQL Operation Utility Class
 * 
 * @author ruoyi
 */
public class SqlUtil
{
    /**
     * Define common SQL keywords
     */
    public static String SQL_REGEX = "\u000B|and |extractvalue|updatexml|sleep|exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |or |union |like |+|/*|user()";

    /**
     * Only supports letters, numbers, underscores, spaces, commas, decimal points (supports multi-field sorting)
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * Limit orderBy maximum length
     */
    private static final int ORDER_BY_MAX_LENGTH = 500;

    /**
     * Check characters to prevent injection bypass
     */
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            throw new UtilException("Parameter does not meet specifications, cannot perform query");
        }
        if (StringUtils.length(value) > ORDER_BY_MAX_LENGTH)
        {
            throw new UtilException("Parameter has exceeded maximum limit, cannot perform query");
        }
        return value;
    }

    /**
     * Validate if order by syntax meets specifications
     */
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }

    /**
     * SQL keyword check
     */
    public static void filterKeyword(String value)
    {
        if (StringUtils.isEmpty(value))
        {
            return;
        }
        String[] sqlKeywords = StringUtils.split(SQL_REGEX, "\\|");
        for (String sqlKeyword : sqlKeywords)
        {
            if (StringUtils.indexOfIgnoreCase(value, sqlKeyword) > -1)
            {
                throw new UtilException("Parameter contains SQL injection risk");
            }
        }
    }
}
