package com.ruoyi.common.core.exception.auth;

/**
 * Failed login authentication exception
 *
 * @author ruoyi
 */
public class NotLoginException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotLoginException(String message)
    {
        super(message);
    }
}
