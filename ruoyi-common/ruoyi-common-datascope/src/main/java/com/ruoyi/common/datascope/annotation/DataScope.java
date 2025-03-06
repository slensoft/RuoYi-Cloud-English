package com.ruoyi.common.datascope.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Data permission filtering annotation
 * 
 * @author ruoyi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * Alias for the department table
     */
    public String deptAlias() default "";

    /**
     * Alias for the user table
     */
    public String userAlias() default "";

    /**
     * Permission character (used to match permissions that meet the requirements for multiple roles) defaults to obtaining based on the permission annotation @RequiresPermissions, multiple permissions are separated by commas
     */
    public String permission() default "";
}
