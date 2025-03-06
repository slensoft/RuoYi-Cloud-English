package com.ruoyi.common.core.utils.bean;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 * Bean Object Property Validation
 * 
 * @author ruoyi
 */
public class BeanValidators
{
    public static void validateWithException(Validator validator, Object object, Class<?>... groups)
            throws ConstraintViolationException
    {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty())
        {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
