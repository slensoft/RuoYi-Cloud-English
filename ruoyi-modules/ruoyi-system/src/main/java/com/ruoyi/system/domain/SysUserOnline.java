package com.ruoyi.system.domain;

/**
 * Current Online Session
 * 
 * @author ruoyi
 */
public class SysUserOnline
{
    /** Session ID */
    private String tokenId;

    /** User Name */
    private String userName;

    /** Login IP Address */
    private String ipaddr;

    /** Login Location */
    private String loginLocation;

    /** Browser Type */
    private String browser;

    /** Operating System */
    private String os;

    /** Login Time */
    private Long loginTime;

    public String getTokenId()
    {
        return tokenId;
    }

    public void setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }
}
