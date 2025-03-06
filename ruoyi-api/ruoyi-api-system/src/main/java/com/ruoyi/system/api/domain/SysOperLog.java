package com.ruoyi.system.api.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.annotation.Excel.ColumnType;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * Operation log record table oper_log
 *
 * @author ruoyi
 */
public class SysOperLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Operation log ID */
    @Excel(name = "Operation log ID", cellType = ColumnType.NUMERIC)
    private Long operId;

    /** Operation module */
    @Excel(name = "Operation module")
    private String title;

    /** Business type (0 other 1 new 2 modify 3 delete) */
    @Excel(name = "Business type", readConverterExp = "0=other,1=new,2=modify,3=delete,4=authorize,5=export,6=import,7=force quit,8=generate code,9=clear data")
    private Integer businessType;

    /** Business type array */
    private Integer[] businessTypes;

    /** Request method */
    @Excel(name = "Request method")
    private String method;

    /** Request method */
    @Excel(name = "Request method")
    private String requestMethod;

    /** Operation category (0 other 1 backend user 2 mobile user) */
    @Excel(name = "Operation category", readConverterExp = "0=other,1=backend user,2=mobile user")
    private Integer operatorType;

    /** Operator */
    @Excel(name = "Operator")
    private String operName;

    /** Department name */
    @Excel(name = "Department name")
    private String deptName;

    /** Request url */
    @Excel(name = "Request address")
    private String operUrl;

    /** Operation address */
    @Excel(name = "Operation address")
    private String operIp;

    /** Request parameters */
    @Excel(name = "Request parameters")
    private String operParam;

    /** Return parameters */
    @Excel(name = "Return parameters")
    private String jsonResult;

    /** Operation status (0 normal 1 abnormal) */
    @Excel(name = "Status", readConverterExp = "0=normal,1=abnormal")
    private Integer status;

    /** Error message */
    @Excel(name = "Error message")
    private String errorMsg;

    /** Operation time */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "Operation time", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

    /** Time consumed */
    @Excel(name = "Time consumed", suffix = "milliseconds")
    private Long costTime;

    public Long getOperId()
    {
        return operId;
    }

    public void setOperId(Long operId)
    {
        this.operId = operId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Integer getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(Integer businessType)
    {
        this.businessType = businessType;
    }

    public Integer[] getBusinessTypes()
    {
        return businessTypes;
    }

    public void setBusinessTypes(Integer[] businessTypes)
    {
        this.businessTypes = businessTypes;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getRequestMethod()
    {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod)
    {
        this.requestMethod = requestMethod;
    }

    public Integer getOperatorType()
    {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType)
    {
        this.operatorType = operatorType;
    }

    public String getOperName()
    {
        return operName;
    }

    public void setOperName(String operName)
    {
        this.operName = operName;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getOperUrl()
    {
        return operUrl;
    }

    public void setOperUrl(String operUrl)
    {
        this.operUrl = operUrl;
    }

    public String getOperIp()
    {
        return operIp;
    }

    public void setOperIp(String operIp)
    {
        this.operIp = operIp;
    }

    public String getOperParam()
    {
        return operParam;
    }

    public void setOperParam(String operParam)
    {
        this.operParam = operParam;
    }

    public String getJsonResult()
    {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult)
    {
        this.jsonResult = jsonResult;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public Date getOperTime()
    {
        return operTime;
    }

    public void setOperTime(Date operTime)
    {
        this.operTime = operTime;
    }

    public Long getCostTime()
    {
        return costTime;
    }

    public void setCostTime(Long costTime)
    {
        this.costTime = costTime;
    }
}
