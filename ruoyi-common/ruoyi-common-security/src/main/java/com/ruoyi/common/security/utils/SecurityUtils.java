package com.ruoyi.common.security.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.api.model.LoginUser;

/**
 * Permission acquisition utility class
 * 
 * @author ruoyi
 */
public class SecurityUtils
{
    /**
     * Get user ID
     */
    public static Long getUserId()
    {
        return SecurityContextHolder.getUserId();
    }

    /**
     * Get user name
     */
    public static String getUsername()
    {
        return SecurityContextHolder.getUserName();
    }

    /**
     * Get user key
     */
    public static String getUserKey()
    {
        return SecurityContextHolder.getUserKey();
    }

    /**
     * Get login user information
     */
    public static LoginUser getLoginUser()
    {
        return SecurityContextHolder.get(SecurityConstants.LOGIN_USER, LoginUser.class);
    }

    /**
     * Get request token
     */
    public static String getToken()
    {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * Get request token based on request
     */
    public static String getToken(HttpServletRequest request)
    {
        // Get token identifier from header
        String token = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        return replaceTokenPrefix(token);
    }

    /**
     * Trim token prefix
     */
    public static String replaceTokenPrefix(String token)
    {
        // If the front end sets a token prefix, trim the prefix
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        return token;
    }

    /**
     * Whether it is an administrator
     * 
     * @param userId User ID
     * @return Result
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    /**
     * Generate BCryptPasswordEncoder password
     *
     * @param password Password
     * @return Encrypted string
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * Determine whether the passwords are the same
     *
     * @param rawPassword Real password
     * @param encodedPassword Encrypted character
     * @return Result
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
