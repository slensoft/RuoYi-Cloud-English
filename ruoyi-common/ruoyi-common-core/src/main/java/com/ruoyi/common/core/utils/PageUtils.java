package com.ruoyi.common.core.utils;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.utils.sql.SqlUtil;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableSupport;

/**
 * Pagination Utility Class
 * 
 * @author ruoyi
 */
public class PageUtils extends PageHelper
{
    /**
     * Set request pagination data
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * Clear pagination thread variables
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
