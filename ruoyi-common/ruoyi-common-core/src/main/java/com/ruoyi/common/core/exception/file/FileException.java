package com.ruoyi.common.core.exception.file;

import com.ruoyi.common.core.exception.base.BaseException;

/**
 * File information exception class
 *
 * @author ruoyi
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args, String msg)
    {
        super("file", code, args, msg);
    }

}
