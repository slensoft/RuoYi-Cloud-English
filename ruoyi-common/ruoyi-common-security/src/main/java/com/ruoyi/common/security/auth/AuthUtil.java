package com.ruoyi.common.security.auth;

import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.annotation.RequiresRoles;
import com.ruoyi.system.api.model.LoginUser;

/**
 * Token permission verification utility class
 * 
 * @author ruoyi
 */
public class AuthUtil
{
    /**
     * Underlying AuthLogic object
     */
    public static AuthLogic authLogic = new AuthLogic();

    /**
     * Session logout
     */
    public static void logout()
    {
        authLogic.logout();
    }

    /**
     * Session logout, based on specified Token
     * 
     * @param token Specified token
     */
    public static void logoutByToken(String token)
    {
        authLogic.logoutByToken(token);
    }

    /**
     * Check if the current session is logged in, if not, throw an exception
     */
    public static void checkLogin()
    {
        authLogic.checkLogin();
    }

    /**
     * Get current login user information
     * 
     * @param token Specified token
     * @return User information
     */
    public static LoginUser getLoginUser(String token)
    {
        return authLogic.getLoginUser(token);
    }

    /**
     * Verify the validity period of the current user
     * 
     * @param loginUser User information
     */
    public static void verifyLoginUserExpire(LoginUser loginUser)
    {
        authLogic.verifyLoginUserExpire(loginUser);
    }

    /**
     * Whether the current account has a specified role identifier, return true or false
     * 
     * @param role Role identifier
     * @return Whether it has the specified role identifier
     */
    public static boolean hasRole(String role)
    {
        return authLogic.hasRole(role);
    }

    /**
     * Whether the current account has a specified role identifier, if the verification fails, throw an exception: NotRoleException
     * 
     * @param role Role identifier
     */
    public static void checkRole(String role)
    {
        authLogic.checkRole(role);
    }

    /**
     * Authorization based on annotation parameters, if the verification fails, throw an exception: NotRoleException
     * 
     * @param requiresRoles Role permission annotation
     */
    public static void checkRole(RequiresRoles requiresRoles)
    {
        authLogic.checkRole(requiresRoles);
    }

    /**
     * Whether the current account has a specified role identifier [Specify multiple, all must pass verification]
     * 
     * @param roles Role identifier array
     */
    public static void checkRoleAnd(String... roles)
    {
        authLogic.checkRoleAnd(roles);
    }

    /**
     * Whether the current account has a specified role identifier [Specify multiple, only one needs to pass verification]
     * 
     * @param roles Role identifier array
     */
    public static void checkRoleOr(String... roles)
    {
        authLogic.checkRoleOr(roles);
    }

    /**
     * Whether the current account has a specified permission, return true or false
     * 
     * @param permission Permission code
     * @return Whether it has the specified permission
     */
    public static boolean hasPermi(String permission)
    {
        return authLogic.hasPermi(permission);
    }

    /**
     * Whether the current account has a specified permission, if the verification fails, throw an exception: NotPermissionException
     * 
     * @param permission Permission code
     */
    public static void checkPermi(String permission)
    {
        authLogic.checkPermi(permission);
    }

    /**
     * Authorization based on annotation parameters, if the verification fails, throw an exception: NotPermissionException
     * 
     * @param requiresPermissions Permission annotation
     */
    public static void checkPermi(RequiresPermissions requiresPermissions)
    {
        authLogic.checkPermi(requiresPermissions);
    }

    /**
     * Whether the current account has a specified permission [Specify multiple, all must pass verification]
     * 
     * @param permissions Permission code array
     */
    public static void checkPermiAnd(String... permissions)
    {
        authLogic.checkPermiAnd(permissions);
    }

    /**
     * Whether the current account has a specified permission [Specify multiple, only one needs to pass verification]
     * 
     * @param permissions Permission code array
     */
    public static void checkPermiOr(String... permissions)
    {
        authLogic.checkPermiOr(permissions);
    }
}
