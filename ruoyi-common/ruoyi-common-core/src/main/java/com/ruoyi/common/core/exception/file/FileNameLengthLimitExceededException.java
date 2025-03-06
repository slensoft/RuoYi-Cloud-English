package com.ruoyi.common.core.exception.file;

/**
 * File name length limit exceeded exception class
 *
 * @author ruoyi
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength }, "the filename is too long");
    }
}
