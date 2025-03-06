package com.ruoyi.common.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Permission authentication: Must have specified permissions to access this method
 * 
 * @author ruoyi
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface RequiresPermissions
{
    /**
     * Permission codes to be verified
     */
    String[] value() default {};

    /**
     * Verification mode: AND | OR, default is AND
     */
    Logical logical() default Logical.AND;
}
