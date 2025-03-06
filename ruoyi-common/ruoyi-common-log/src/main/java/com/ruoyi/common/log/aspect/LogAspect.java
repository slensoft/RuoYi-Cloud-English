package com.ruoyi.common.log.aspect;

import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessStatus;
import com.ruoyi.common.log.filter.PropertyPreExcludeFilter;
import com.ruoyi.common.log.service.AsyncLogService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysOperLog;

/**
 * Operation log processing
 * 
 * @author ruoyi
 */
@Aspect
@Component
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /** Exclude sensitive attribute fields */
    public static final String[] EXCLUDE_PROPERTIES = { "password", "oldPassword", "newPassword", "confirmPassword" };

    /** Calculate operation time consumption */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    @Autowired
    private AsyncLogService asyncLogService;

    /**
     * Execute before processing the request
     */
    @Before(value = "@annotation(controllerLog)")
    public void boBefore(JoinPoint joinPoint, Log controllerLog)
    {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * Execute after processing the request
     *
     * @param joinPoint Join point
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult)
    {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * Intercept exception operations
     * 
     * @param joinPoint Join point
     * @param e Exception
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e)
    {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult)
    {
        try
        {
            // *========Database log=========*//
            SysOperLog operLog = new SysOperLog();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // Request address
            String ip = IpUtils.getIpAddr();
            operLog.setOperIp(ip);
            operLog.setOperUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
            String username = SecurityUtils.getUsername();
            if (StringUtils.isNotBlank(username))
            {
                operLog.setOperName(username);
            }

            if (e != null)
            {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // Set method name
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // Set request method
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // Handle parameters set on the annotation
            getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            // Set consumption time
            operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            // Save to database
            asyncLogService.saveSysLog(operLog);
        }
        catch (Exception exp)
        {
            // Record local exception log
            log.error("Exception information:{}", exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * Get the description information of the method in the annotation for the Controller layer annotation
     * 
     * @param log Log
     * @param operLog Operation log
     * @throws Exception Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog, Object jsonResult) throws Exception
    {
        // Set action
        operLog.setBusinessType(log.businessType().ordinal());
        // Set title
        operLog.setTitle(log.title());
        // Set operator type
        operLog.setOperatorType(log.operatorType().ordinal());
        // Whether to save request, parameters, and values
        if (log.isSaveRequestData())
        {
            // Get the parameter information and pass it to the database.
            setRequestValue(joinPoint, operLog, log.excludeParamNames());
        }
        // Whether to save response, parameters, and values
        if (log.isSaveResponseData() && StringUtils.isNotNull(jsonResult))
        {
            operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }

    /**
     * Get the request parameters and put them in the log
     * 
     * @param operLog Operation log
     * @throws Exception Exception
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog, String[] excludeParamNames) throws Exception
    {
        Map<?, ?> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
        String requestMethod = operLog.getRequestMethod();
        if (StringUtils.isEmpty(paramsMap) && StringUtils.equalsAny(requestMethod, HttpMethod.PUT.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name()))
        {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
        }
        else
        {
            operLog.setOperParam(StringUtils.substring(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeParamNames)), 0, 2000));
        }
    }

    /**
     * Parameter assembly
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (Object o : paramsArray)
            {
                if (StringUtils.isNotNull(o) && !isFilterObject(o))
                {
                    try
                    {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * Ignore sensitive attributes
     */
    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames)
    {
        return new PropertyPreExcludeFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
    }

    /**
     * Determine whether the object needs to be filtered.
     * 
     * @param o Object information.
     * @return If it is an object that needs to be filtered, return true; otherwise, return false.
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
