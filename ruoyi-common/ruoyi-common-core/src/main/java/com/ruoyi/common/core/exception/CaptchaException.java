package com.ruoyi.common.core.exception;

/**
 * Captcha error exception class
 *
 * @author ruoyi
 */
public class CaptchaException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg)
    {
        super(msg);
    }
}
