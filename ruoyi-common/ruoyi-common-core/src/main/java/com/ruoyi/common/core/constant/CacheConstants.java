package com.ruoyi.common.core.constant;

/**
 * Cache constant information
 * 
 * @author ruoyi
 */
public class CacheConstants
{
    /**
     * Cache expiration time, default 720 (minutes)
     */
    public final static long EXPIRATION = 720;

    /**
     * Cache refresh time, default 120 (minutes)
     */
    public final static long REFRESH_TIME = 120;

    /**
     * Maximum password error count
     */
    public final static int PASSWORD_MAX_RETRY_COUNT = 5;

    /**
     * Password lock time, default 10 (minutes)
     */
    public final static long PASSWORD_LOCK_TIME = 10;

    /**
     * Permission cache prefix
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * Captcha redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * Parameter management cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * Dictionary management cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * Login account password error count redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * Login IP blacklist cache key
     */
    public static final String SYS_LOGIN_BLACKIPLIST = SYS_CONFIG_KEY + "sys.login.blackIPList";
}
