package com.ruoyi.common.core.web.domain;

import java.util.HashMap;
import java.util.Objects;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.utils.StringUtils;

/**
 * Operation message response
 * 
 * @author ruoyi
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** Status code */
    public static final String CODE_TAG = "code";

    /** Response message */
    public static final String MSG_TAG = "msg";

    /** Data object */
    public static final String DATA_TAG = "data";

    /**
     * Initialize a newly created AjaxResult object to represent an empty message.
     */
    public AjaxResult()
    {
    }

    /**
     * Initialize a newly created AjaxResult object
     * 
     * @param code Status code
     * @param msg Response message
     */
    public AjaxResult(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * Initialize a newly created AjaxResult object
     * 
     * @param code Status code
     * @param msg Response message
     * @param data Data object
     */
    public AjaxResult(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * Return success message
     * 
     * @return Success message
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("Operation successful");
    }

    /**
     * Return success data
     * 
     * @return Success message
     */
    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("Operation successful", data);
    }

    /**
     * Return success message
     * 
     * @param msg Response message
     * @return Success message
     */
    public static AjaxResult success(String msg)
    {
        return AjaxResult.success(msg, null);
    }

    /**
     * Return success message
     * 
     * @param msg Response message
     * @param data Data object
     * @return Success message
     */
    public static AjaxResult success(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * Return warning message
     *
     * @param msg Response message
     * @return Warning message
     */
    public static AjaxResult warn(String msg)
    {
        return AjaxResult.warn(msg, null);
    }

    /**
     * Return warning message
     *
     * @param msg Response message
     * @param data Data object
     * @return Warning message
     */
    public static AjaxResult warn(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.WARN, msg, data);
    }

    /**
     * Return error message
     * 
     * @return Error message
     */
    public static AjaxResult error()
    {
        return AjaxResult.error("Operation failed");
    }

    /**
     * Return error message
     * 
     * @param msg Response message
     * @return Error message
     */
    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

    /**
     * Return error message
     * 
     * @param msg Response message
     * @param data Data object
     * @return Error message
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * Return error message
     * 
     * @param code Status code
     * @param msg Response message
     * @return Error message
     */
    public static AjaxResult error(int code, String msg)
    {
        return new AjaxResult(code, msg, null);
    }

    /**
     * Check if it's a success message
     *
     * @return Result
     */
    public boolean isSuccess()
    {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * Check if it's a warning message
     *
     * @return Result
     */
    public boolean isWarn()
    {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * Check if it's an error message
     *
     * @return Result
     */
    public boolean isError()
    {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    /**
     * Convenient for method chaining
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
