package com.ruoyi.common.security.handler;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.DemoModeException;
import com.ruoyi.common.core.exception.InnerAuthException;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.exception.auth.NotPermissionException;
import com.ruoyi.common.core.exception.auth.NotRoleException;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.html.EscapeUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;

/**
 * Global exception handler
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Permission code exception
     */
    @ExceptionHandler(NotPermissionException.class)
    public AjaxResult handleNotPermissionException(NotPermissionException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("Request URL '{}', permission code verification failed '{}'", requestURI, e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN, "No access permission, please contact administrator for authorization");
    }

    /**
     * Role permission exception
     */
    @ExceptionHandler(NotRoleException.class)
    public AjaxResult handleNotRoleException(NotRoleException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("Request URL '{}', role permission verification failed '{}'", requestURI, e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN, "No access permission, please contact administrator for authorization");
    }

    /**
     * Request method not supported
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("Request URL '{}', method '{}' not supported", requestURI, e.getMethod());
        return AjaxResult.error(e.getMessage());
    }

    /**
     * Business exception
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        return StringUtils.isNotNull(code) ? AjaxResult.error(code, e.getMessage()) : AjaxResult.error(e.getMessage());
    }

    /**
     * Missing required path variable in request path
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public AjaxResult handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("Required path variable missing in request path '{}', system exception occurred.", requestURI, e);
        return AjaxResult.error(String.format("Required path variable [%s] missing in request path", e.getVariableName()));
    }

    /**
     * Request parameter type mismatch
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public AjaxResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        String value = Convert.toStr(e.getValue());
        if (StringUtils.isNotEmpty(value))
        {
            value = EscapeUtil.clean(value);
        }
        log.error("Parameter type mismatch at request URL '{}', system exception occurred.", requestURI, e);
        return AjaxResult.error(String.format("Parameter type mismatch, parameter [%s] requires type: '%s', but input value was: '%s'", e.getName(), e.getRequiredType().getName(), value));
    }

    /**
     * Intercept unknown runtime exceptions
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("Unknown exception occurred at request URL '{}'.", requestURI, e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * System exception
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("System exception occurred at request URL '{}'.", requestURI, e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * Custom validation exception
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult handleBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }

    /**
     * Custom validation exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return AjaxResult.error(message);
    }

    /**
     * Internal authentication exception
     */
    @ExceptionHandler(InnerAuthException.class)
    public AjaxResult handleInnerAuthException(InnerAuthException e)
    {
        return AjaxResult.error(e.getMessage());
    }

    /**
     * Demo mode exception
     */
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult handleDemoModeException(DemoModeException e)
    {
        return AjaxResult.error("Operation not allowed in demo mode");
    }
}
