package com.ruoyi.system.service;

import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.SysUserOnline;

/**
 * Online User Service Layer
 * 
 * @author ruoyi
 */
public interface ISysUserOnlineService
{
    /**
     * Query information by login address
     * 
     * @param ipaddr Login address
     * @param user User information
     * @return Online user information
     */
    public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user);

    /**
     * Query information by username
     * 
     * @param userName Username
     * @param user User information
     * @return Online user information
     */
    public SysUserOnline selectOnlineByUserName(String userName, LoginUser user);

    /**
     * Query information by login address/username
     * 
     * @param ipaddr Login address
     * @param userName Username
     * @param user User information
     * @return Online user information
     */
    public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user);

    /**
     * Set online user information
     * 
     * @param user User information
     * @return Online user
     */
    public SysUserOnline loginUserToUserOnline(LoginUser user);
}
