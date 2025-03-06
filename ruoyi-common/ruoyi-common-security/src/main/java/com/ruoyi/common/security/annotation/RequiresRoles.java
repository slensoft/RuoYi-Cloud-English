package com.ruoyi.common.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Role authentication: Must have specified role identifier to access this method
 * 
 * @author ruoyi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface RequiresRoles
{
    /**
     * Role identifiers to be verified
     */
    String[] value() default {};

    /**
     * Verification logic: AND | OR, default is AND
     */
    Logical logical() default Logical.AND;
}
