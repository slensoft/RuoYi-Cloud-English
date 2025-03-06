package com.ruoyi.system.service.impl;

import org.springframework.stereotype.Service;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.SysUserOnline;
import com.ruoyi.system.service.ISysUserOnlineService;

/**
 * Online User Service Layer Processing
 * 
 * @author ruoyi
 */
@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService
{
    /**
     * Query information by login address
     * 
     * @param ipaddr Login address
     * @param user User information
     * @return Online user information
     */
    @Override
    public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user)
    {
        if (StringUtils.equals(ipaddr, user.getIpaddr()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * Query information by username
     * 
     * @param userName Username
     * @param user User information
     * @return Online user information
     */
    @Override
    public SysUserOnline selectOnlineByUserName(String userName, LoginUser user)
    {
        if (StringUtils.equals(userName, user.getUsername()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * Query information by login address/username
     * 
     * @param ipaddr Login address
     * @param userName Username
     * @param user User information
     * @return Online user information
     */
    @Override
    public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user)
    {
        if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
        {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    /**
     * Set online user information
     * 
     * @param user User information
     * @return Online user
     */
    @Override
    public SysUserOnline loginUserToUserOnline(LoginUser user)
    {
        if (StringUtils.isNull(user))
        {
            return null;
        }
        SysUserOnline sysUserOnline = new SysUserOnline();
        sysUserOnline.setTokenId(user.getToken());
        sysUserOnline.setUserName(user.getUsername());
        sysUserOnline.setIpaddr(user.getIpaddr());
        sysUserOnline.setLoginTime(user.getLoginTime());
        return sysUserOnline;
    }
}
