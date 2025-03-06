package com.ruoyi.common.security.annotation;

import java.lang.annotation.*;

/**
 * Internal authentication annotation
 * 
 * @author ruoyi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerAuth
{
    /**
     * Whether to verify user information
     */
    boolean isUser() default false;
}