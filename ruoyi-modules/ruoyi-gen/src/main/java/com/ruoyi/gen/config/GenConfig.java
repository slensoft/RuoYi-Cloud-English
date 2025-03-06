package com.ruoyi.gen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Code generation related configuration
 * 
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "gen")
public class GenConfig
{
    /** Author */
    public static String author;

    /** Package path for generation */
    public static String packageName;

    /** Automatically remove table prefix */
    public static boolean autoRemovePre;

    /** Table prefix */
    public static String tablePrefix;

    /** Whether to allow file generation to overwrite locally (custom path) */
    public static boolean allowOverwrite;

    public static String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        GenConfig.author = author;
    }

    public static String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        GenConfig.packageName = packageName;
    }

    public static boolean getAutoRemovePre()
    {
        return autoRemovePre;
    }

    public void setAutoRemovePre(boolean autoRemovePre)
    {
        GenConfig.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix()
    {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix)
    {
        GenConfig.tablePrefix = tablePrefix;
    }

    public static boolean isAllowOverwrite()
    {
        return allowOverwrite;
    }

    public void setAllowOverwrite(boolean allowOverwrite)
    {
        GenConfig.allowOverwrite = allowOverwrite;
    }
}
