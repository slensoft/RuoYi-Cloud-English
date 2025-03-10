package com.ruoyi.auth.form;

/**
 * User login object
 *
 * @author ruoyi
 */
public class LoginBody
{
    /**
     * Username
     */
    private String username;

    /**
     * User password
     */
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
