package com.ruoyi.system.api.model;

import java.io.Serializable;
import java.util.Set;
import com.ruoyi.system.api.domain.SysUser;

/**
 * User information
 *
 * @author ruoyi
 */
public class LoginUser implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * User unique identifier
     */
    private String token;

    /**
     * Username id
     */
    private Long userid;

    /**
     * Username
     */
    private String username;

    /**
     * Login time
     */
    private Long loginTime;

    /**
     * Expiration time
     */
    private Long expireTime;

    /**
     * Login IP address
     */
    private String ipaddr;

    /**
     * Permission list
     */
    private Set<String> permissions;

    /**
     * Role list
     */
    private Set<String> roles;

    /**
     * User information
     */
    private SysUser sysUser;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Long getUserid()
    {
        return userid;
    }

    public void setUserid(Long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }

    public Set<String> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<String> roles)
    {
        this.roles = roles;
    }

    public SysUser getSysUser()
    {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser)
    {
        this.sysUser = sysUser;
    }
}
