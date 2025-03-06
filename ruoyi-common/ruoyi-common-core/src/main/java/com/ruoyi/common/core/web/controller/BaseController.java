package com.ruoyi.common.core.web.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.PageUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * Common data processing for web layer
 * 
 * @author ruoyi
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Automatically convert date format strings passed from frontend to Date type
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date type conversion
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * Set request pagination data
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }

    /**
     * Clear pagination thread variables
     */
    protected void clearPage()
    {
        PageUtils.clearPage();
    }

    /**
     * Response to request pagination data
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setMsg("Query successful");
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * Return success
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * Return success message
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * Return success message
     */
    public AjaxResult success(Object data)
    {
        return AjaxResult.success(data);
    }

    /**
     * Return failure message
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * Return failure message
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * Return warning message
     */
    public AjaxResult warn(String message)
    {
        return AjaxResult.warn(message);
    }

    /**
     * Response return result
     * 
     * @param rows Number of affected rows
     * @return Operation result
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * Response return result
     * 
     * @param result Result
     * @return Operation result
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }
}
