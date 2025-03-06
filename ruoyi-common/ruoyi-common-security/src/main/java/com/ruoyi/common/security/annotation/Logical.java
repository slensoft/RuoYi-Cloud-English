package com.ruoyi.common.security.annotation;

/**
 * Verification mode for permission annotations
 * 
 * @author ruoyi
 *
 */
public enum Logical
{
    /**
     * Must have all elements
     */
    AND,

    /**
     * Only need to have one of the elements
     */
    OR
}
