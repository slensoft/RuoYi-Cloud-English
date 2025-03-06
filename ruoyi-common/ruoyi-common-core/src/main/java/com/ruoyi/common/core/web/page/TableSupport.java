package com.ruoyi.common.core.web.page;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.ServletUtils;

/**
 * Table data processing
 * 
 * @author ruoyi
 */
public class TableSupport
{
    /**
     * Current record starting index
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
     * Sort direction "desc" or "asc"
     */
    public static final String IS_ASC = "isAsc";

    /**
     * Pagination parameter validation
     */
    public static final String REASONABLE = "reasonable";

    /**
     * Encapsulate pagination object
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Convert.toInt(ServletUtils.getParameter(PAGE_NUM), 1));
        pageDomain.setPageSize(Convert.toInt(ServletUtils.getParameter(PAGE_SIZE), 10));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        pageDomain.setReasonable(ServletUtils.getParameterToBool(REASONABLE));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
