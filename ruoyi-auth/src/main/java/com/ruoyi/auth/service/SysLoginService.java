package com.ruoyi.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteUserService;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;

/**
 * Login verification method
 *
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysRecordLogService recordLogService;

    @Autowired
    private RedisService redisService;

    /**
     * Login
     */
    public LoginUser login(String username, String password)
    {
        // Username or password is empty error
        if (StringUtils.isAnyBlank(username, password))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "User/password must be filled in");
            throw new ServiceException("User/password must be filled in");
        }
        // If the password is not within the specified range error
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "User password is not within the specified range");
            throw new ServiceException("User password is not within the specified range");
        }
        // Username is not within the specified range error
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "Username is not within the specified range");
            throw new ServiceException("Username is not within the specified range");
        }
        // IP blacklist verification
        String blackStr = Convert.toStr(redisService.getCacheObject(CacheConstants.SYS_LOGIN_BLACKIPLIST));
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "Unfortunately, the access IP has been blacklisted by the system");
            throw new ServiceException("Unfortunately, the access IP has been blacklisted by the system");
        }
        // Query user information
        R<LoginUser> userResult = remoteUserService.getUserInfo(username, SecurityConstants.INNER);

        if (R.FAIL == userResult.getCode())
        {
            throw new ServiceException(userResult.getMsg());
        }

        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "Sorry, your account has been deleted");
            throw new ServiceException("Sorry, your account: " + username + " has been deleted");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "User has been disabled, please contact the administrator");
            throw new ServiceException("Sorry, your account: " + username + " has been disabled");
        }
        passwordService.validate(user, password);
        recordLogService.recordLogininfor(username, Constants.LOGIN_SUCCESS, "Login successful");
        recordLoginInfo(user.getUserId());
        return userInfo;
    }

    /**
     * Record login information
     *
     * @param userId User ID
     */
    public void recordLoginInfo(Long userId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        // Update user login IP
        sysUser.setLoginIp(IpUtils.getIpAddr());
        // Update user login time
        sysUser.setLoginDate(DateUtils.getNowDate());
        remoteUserService.recordUserLogin(sysUser, SecurityConstants.INNER);
    }

    public void logout(String loginName)
    {
        recordLogService.recordLogininfor(loginName, Constants.LOGOUT, "Logout successful");
    }

    /**
     * Register
     */
    public void register(String username, String password)
    {
        // Username or password is empty error
        if (StringUtils.isAnyBlank(username, password))
        {
            throw new ServiceException("User/password must be filled in");
        }
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            throw new ServiceException("Account length must be between 2 and 20 characters");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new ServiceException("Password length must be between 5 and 20 characters");
        }

        // Register user information
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setNickName(username);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        R<?> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);

        if (R.FAIL == registerResult.getCode())
        {
            throw new ServiceException(registerResult.getMsg());
        }
        recordLogService.recordLogininfor(username, Constants.REGISTER, "Registration successful");
    }
}
