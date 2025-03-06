package com.ruoyi.common.core.constant;

/**
 * General constant information
 * 
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 character set
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK character set
     */
    public static final String GBK = "GBK";

    /**
     * www main domain
     */
    public static final String WWW = "www.";

    /**
     * RMI remote method invocation
     */
    public static final String LOOKUP_RMI = "rmi:";

    /**
     * LDAP remote method invocation
     */
    public static final String LOOKUP_LDAP = "ldap:";

    /**
     * LDAPS remote method invocation
     */
    public static final String LOOKUP_LDAPS = "ldaps:";

    /**
     * http request
     */
    public static final String HTTP = "http://";

    /**
     * https request
     */
    public static final String HTTPS = "https://";

    /**
     * Success flag
     */
    public static final Integer SUCCESS = 200;

    /**
     * Failure flag
     */
    public static final Integer FAIL = 500;

    /**
     * Login success status
     */
    public static final String LOGIN_SUCCESS_STATUS = "0";

    /**
     * Login failure status
     */
    public static final String LOGIN_FAIL_STATUS = "1";

    /**
     * Login success
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * Logout
     */
    public static final String LOGOUT = "Logout";

    /**
     * Register
     */
    public static final String REGISTER = "Register";

    /**
     * Login failure
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * Current record start index
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * Number of records per page
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * Sort column
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * Sort direction "desc" or "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * Verification code validity period (minutes)
     */
    public static final long CAPTCHA_EXPIRATION = 2;

    /**
     * Resource mapping path prefix
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * Automatic recognition json object white list configuration (only allow parsed package names, the smaller the range the safer)
     */
    public static final String[] JSON_WHITELIST_STR = { "org.springframework", "com.ruoyi" };

    /**
     * Scheduled task white list configuration (only allow access package names, if other needs can be added by yourself)
     */
    public static final String[] JOB_WHITELIST_STR = { "com.ruoyi.job.task" };

    /**
     * Scheduled task illegal characters
     */
    public static final String[] JOB_ERROR_STR = { "java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
            "org.springframework", "org.apache", "com.ruoyi.common.core.utils.file" };
}
