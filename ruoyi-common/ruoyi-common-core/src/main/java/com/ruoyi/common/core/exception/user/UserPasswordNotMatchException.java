package com.ruoyi.common.core.exception.user;

/**
 * User Password Incorrect or Does Not Meet Requirements Exception Class
 * 
 * @author ruoyi
 */
public class UserPasswordNotMatchException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("user.password.not.match", null);
    }
}
