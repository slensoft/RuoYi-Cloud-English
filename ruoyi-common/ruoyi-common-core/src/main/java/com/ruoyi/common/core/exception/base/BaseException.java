package com.ruoyi.common.core.exception.base;

/**
 * Base exception
 *
 * @author ruoyi
 */
public class BaseException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * Module
     */
    private String module;

    /**
     * Error code
     */
    private String code;

    /**
     * Parameters corresponding to the error code
     */
    private Object[] args;

    /**
     * Error message
     */
    private String defaultMessage;

    public BaseException(String module, String code, Object[] args, String defaultMessage)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage)
    {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args)
    {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }

    public String getModule()
    {
        return module;
    }

    public String getCode()
    {
        return code;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public String getDefaultMessage()
    {
        return defaultMessage;
    }
}
