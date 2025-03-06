package com.ruoyi.common.security.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.annotation.RequiresRoles;
import com.ruoyi.common.security.auth.AuthUtil;

/**
 * Annotation-based authorization using Spring AOP
 * 
 * @author kong
 */
@Aspect
@Component
public class PreAuthorizeAspect
{
    /**
     * Constructor
     */
    public PreAuthorizeAspect()
    {
    }

    /**
     * Define AOP signature (intercept all methods using authorization annotations)
     */
    public static final String POINTCUT_SIGN = " @annotation(com.ruoyi.common.security.annotation.RequiresLogin) || "
            + "@annotation(com.ruoyi.common.security.annotation.RequiresPermissions) || "
            + "@annotation(com.ruoyi.common.security.annotation.RequiresRoles)";

    /**
     * Declare AOP signature
     */
    @Pointcut(POINTCUT_SIGN)
    public void pointcut()
    {
    }

    /**
     * Around advice
     * 
     * @param joinPoint Join point
     * @return Return value after executing the underlying method
     * @throws Throwable Exception thrown by the underlying method
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        // Annotation authorization
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        try
        {
            // Execute original logic
            Object obj = joinPoint.proceed();
            return obj;
        }
        catch (Throwable e)
        {
            throw e;
        }
    }

    /**
     * Perform annotation check on a Method object
     */
    public void checkMethodAnnotation(Method method)
    {
        // Validate @RequiresLogin annotation
        RequiresLogin requiresLogin = method.getAnnotation(RequiresLogin.class);
        if (requiresLogin != null)
        {
            AuthUtil.checkLogin();
        }

        // Validate @RequiresRoles annotation
        RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);
        if (requiresRoles != null)
        {
            AuthUtil.checkRole(requiresRoles);
        }

        // Validate @RequiresPermissions annotation
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null)
        {
            AuthUtil.checkPermi(requiresPermissions);
        }
    }
}
