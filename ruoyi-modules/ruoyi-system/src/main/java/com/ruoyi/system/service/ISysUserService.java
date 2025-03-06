package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.api.domain.SysUser;

/**
 * User Business Layer
 * 
 * @author ruoyi
 */
public interface ISysUserService
{
    /**
     * Query user list by pagination based on conditions
     * 
     * @param user User information
     * @return User information collection
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     * Query allocated user role list by pagination based on conditions
     * 
     * @param user User information
     * @return User information collection
     */
    public List<SysUser> selectAllocatedList(SysUser user);

    /**
     * Query unallocated user role list by pagination based on conditions
     * 
     * @param user User information
     * @return User information collection
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * Query user by username
     * 
     * @param userName Username
     * @return User object information
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * Query user by user ID
     * 
     * @param userId User ID
     * @return User object information
     */
    public SysUser selectUserById(Long userId);

    /**
     * Query user's role groups by username
     * 
     * @param userName Username
     * @return Result
     */
    public String selectUserRoleGroup(String userName);

    /**
     * Query user's post groups by username
     * 
     * @param userName Username
     * @return Result
     */
    public String selectUserPostGroup(String userName);

    /**
     * Check if username is unique
     * 
     * @param user User information
     * @return Result
     */
    public boolean checkUserNameUnique(SysUser user);

    /**
     * Check if phone number is unique
     *
     * @param user User information
     * @return Result
     */
    public boolean checkPhoneUnique(SysUser user);

    /**
     * Check if email is unique
     *
     * @param user User information
     * @return Result
     */
    public boolean checkEmailUnique(SysUser user);

    /**
     * Check if user operation is allowed
     * 
     * @param user User information
     */
    public void checkUserAllowed(SysUser user);

    /**
     * Check if user has data permissions
     * 
     * @param userId User ID
     */
    public void checkUserDataScope(Long userId);

    /**
     * Add user information
     * 
     * @param user User information
     * @return Result
     */
    public int insertUser(SysUser user);

    /**
     * Register user information
     * 
     * @param user User information
     * @return Result
     */
    public boolean registerUser(SysUser user);

    /**
     * Modify user information
     * 
     * @param user User information
     * @return Result
     */
    public int updateUser(SysUser user);

    /**
     * Authorize user roles
     * 
     * @param userId User ID
     * @param roleIds Role group
     */
    public void insertUserAuth(Long userId, Long[] roleIds);

    /**
     * Modify user status
     * 
     * @param user User information
     * @return Result
     */
    public int updateUserStatus(SysUser user);

    /**
     * Modify user basic information
     * 
     * @param user User information
     * @return Result
     */
    public boolean updateUserProfile(SysUser user);

    /**
     * Modify user avatar
     * 
     * @param userName Username
     * @param avatar Avatar URL
     * @return Result
     */
    public boolean updateUserAvatar(String userName, String avatar);

    /**
     * Reset user password
     * 
     * @param user User information
     * @return Result
     */
    public int resetPwd(SysUser user);

    /**
     * Reset user password
     * 
     * @param userName Username
     * @param password Password
     * @return Result
     */
    public int resetUserPwd(String userName, String password);

    /**
     * Delete user by user ID
     * 
     * @param userId User ID
     * @return Result
     */
    public int deleteUserById(Long userId);

    /**
     * Batch delete user information
     * 
     * @param userIds User IDs to be deleted
     * @return Result
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * Import user data
     * 
     * @param userList User data list
     * @param isUpdateSupport Whether to support update if data exists
     * @param operName Operating user
     * @return Result
     */
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);
}
