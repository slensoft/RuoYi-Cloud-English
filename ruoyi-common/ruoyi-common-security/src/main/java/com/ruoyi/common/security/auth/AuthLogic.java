package com.ruoyi.common.security.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.util.PatternMatchUtils;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.exception.auth.NotLoginException;
import com.ruoyi.common.core.exception.auth.NotPermissionException;
import com.ruoyi.common.core.exception.auth.NotRoleException;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.annotation.Logical;
import com.ruoyi.common.security.annotation.RequiresLogin;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.annotation.RequiresRoles;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;

/**
 * Token Permission Verification, Logic Implementation Class
 * 
 * @author ruoyi
 */
public class AuthLogic
{
    /** All permission identifiers */
    private static final String ALL_PERMISSION = "*:*:*";

    /** Administrator role permission identifier */
    private static final String SUPER_ADMIN = "admin";

    public TokenService tokenService = SpringUtils.getBean(TokenService.class);

    /**
     * Session logout
     */
    public void logout()
    {
        String token = SecurityUtils.getToken();
        if (token == null)
        {
            return;
        }
        logoutByToken(token);
    }

    /**
     * Session logout by specified Token
     */
    public void logoutByToken(String token)
    {
        tokenService.delLoginUser(token);
    }

    /**
     * Check if user is already logged in, if not, throw exception
     */
    public void checkLogin()
    {
        getLoginUser();
    }

    /**
     * Get current user cache information, if not logged in, throw exception
     * 
     * @return User cache information
     */
    public LoginUser getLoginUser()
    {
        String token = SecurityUtils.getToken();
        if (token == null)
        {
            throw new NotLoginException("Token not provided");
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser == null)
        {
            throw new NotLoginException("Invalid token");
        }
        return loginUser;
    }

    /**
     * Get current user cache information, if not logged in, throw exception
     * 
     * @param token Authentication information passed from frontend
     * @return User cache information
     */
    public LoginUser getLoginUser(String token)
    {
        return tokenService.getLoginUser(token);
    }

    /**
     * Verify current user validity period, if difference is less than 120 minutes, automatically refresh cache
     * 
     * @param loginUser Current user information
     */
    public void verifyLoginUserExpire(LoginUser loginUser)
    {
        tokenService.verifyToken(loginUser);
    }

    /**
     * Verify if user has certain permission
     * 
     * @param permission Permission string
     * @return Whether user has certain permission
     */
    public boolean hasPermi(String permission)
    {
        return hasPermi(getPermiList(), permission);
    }

    /**
     * Verify if user has certain permission, if verification fails, throw exception: NotPermissionException
     * 
     * @param permission Permission string
     * @return Whether user has certain permission
     */
    public void checkPermi(String permission)
    {
        if (!hasPermi(getPermiList(), permission))
        {
            throw new NotPermissionException(permission);
        }
    }

    /**
     * Verify permissions based on annotation(@RequiresPermissions), if verification fails, throw exception: NotPermissionException
     * 
     * @param requiresPermissions Annotation object
     */
    public void checkPermi(RequiresPermissions requiresPermissions)
    {
        SecurityContextHolder.setPermission(StringUtils.join(requiresPermissions.value(), ","));
        if (requiresPermissions.logical() == Logical.AND)
        {
            checkPermiAnd(requiresPermissions.value());
        }
        else
        {
            checkPermiOr(requiresPermissions.value());
        }
    }

    /**
     * Verify if user has specified permissions, must have all
     *
     * @param permissions Permission list
     */
    public void checkPermiAnd(String... permissions)
    {
        Set<String> permissionList = getPermiList();
        for (String permission : permissions)
        {
            if (!hasPermi(permissionList, permission))
            {
                throw new NotPermissionException(permission);
            }
        }
    }

    /**
     * Verify if user has specified permissions, only need one of them
     * 
     * @param permissions Permission code array
     */
    public void checkPermiOr(String... permissions)
    {
        Set<String> permissionList = getPermiList();
        for (String permission : permissions)
        {
            if (hasPermi(permissionList, permission))
            {
                return;
            }
        }
        if (permissions.length > 0)
        {
            throw new NotPermissionException(permissions);
        }
    }

    /**
     * Check if user has certain role
     * 
     * @param role Role identifier
     * @return Whether user has certain role
     */
    public boolean hasRole(String role)
    {
        return hasRole(getRoleList(), role);
    }

    /**
     * Check if user has certain role, if verification fails, throw exception: NotRoleException
     * 
     * @param role Role identifier
     */
    public void checkRole(String role)
    {
        if (!hasRole(role))
        {
            throw new NotRoleException(role);
        }
    }

    /**
     * Verify permissions based on annotation(@RequiresRoles)
     * 
     * @param requiresRoles Annotation object
     */
    public void checkRole(RequiresRoles requiresRoles)
    {
        if (requiresRoles.logical() == Logical.AND)
        {
            checkRoleAnd(requiresRoles.value());
        }
        else
        {
            checkRoleOr(requiresRoles.value());
        }
    }

    /**
     * Verify if user has specified roles, must have all
     * 
     * @param roles Role identifier array
     */
    public void checkRoleAnd(String... roles)
    {
        Set<String> roleList = getRoleList();
        for (String role : roles)
        {
            if (!hasRole(roleList, role))
            {
                throw new NotRoleException(role);
            }
        }
    }

    /**
     * Verify if user has specified roles, only need one of them
     * 
     * @param roles Role identifier array
     */
    public void checkRoleOr(String... roles)
    {
        Set<String> roleList = getRoleList();
        for (String role : roles)
        {
            if (hasRole(roleList, role))
            {
                return;
            }
        }
        if (roles.length > 0)
        {
            throw new NotRoleException(roles);
        }
    }

    /**
     * Verify permissions based on annotation(@RequiresLogin)
     * 
     * @param at Annotation object
     */
    public void checkByAnnotation(RequiresLogin at)
    {
        this.checkLogin();
    }

    /**
     * Verify permissions based on annotation(@RequiresRoles)
     * 
     * @param at Annotation object
     */
    public void checkByAnnotation(RequiresRoles at)
    {
        String[] roleArray = at.value();
        if (at.logical() == Logical.AND)
        {
            this.checkRoleAnd(roleArray);
        }
        else
        {
            this.checkRoleOr(roleArray);
        }
    }

    /**
     * Verify permissions based on annotation(@RequiresPermissions)
     * 
     * @param at Annotation object
     */
    public void checkByAnnotation(RequiresPermissions at)
    {
        String[] permissionArray = at.value();
        if (at.logical() == Logical.AND)
        {
            this.checkPermiAnd(permissionArray);
        }
        else
        {
            this.checkPermiOr(permissionArray);
        }
    }

    /**
     * Get current account's role list
     * 
     * @return Role list
     */
    public Set<String> getRoleList()
    {
        try
        {
            LoginUser loginUser = getLoginUser();
            return loginUser.getRoles();
        }
        catch (Exception e)
        {
            return new HashSet<>();
        }
    }

    /**
     * Get current account's permission list
     * 
     * @return Permission list
     */
    public Set<String> getPermiList()
    {
        try
        {
            LoginUser loginUser = getLoginUser();
            return loginUser.getPermissions();
        }
        catch (Exception e)
        {
            return new HashSet<>();
        }
    }

    /**
     * Check if contains permission
     * 
     * @param authorities Permission list
     * @param permission Permission string
     * @return Whether user has certain permission
     */
    public boolean hasPermi(Collection<String> authorities, String permission)
    {
        return authorities.stream().filter(StringUtils::hasText)
                .anyMatch(x -> ALL_PERMISSION.equals(x) || PatternMatchUtils.simpleMatch(x, permission));
    }

    /**
     * Check if contains role
     * 
     * @param roles Role list
     * @param role Role
     * @return Whether user has certain role permission
     */
    public boolean hasRole(Collection<String> roles, String role)
    {
        return roles.stream().filter(StringUtils::hasText)
                .anyMatch(x -> SUPER_ADMIN.equals(x) || PatternMatchUtils.simpleMatch(x, role));
    }
}
