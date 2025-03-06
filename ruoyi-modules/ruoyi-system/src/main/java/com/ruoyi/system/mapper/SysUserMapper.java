package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.api.domain.SysUser;

/**
 * User Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysUserMapper
{
    /**
     * Query user list by condition with pagination
     * 
     * @param sysUser User information
     * @return Collection of user information
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * Query allocated user role list by condition with pagination
     * 
     * @param user User information
     * @return Collection of user information
     */
    public List<SysUser> selectAllocatedList(SysUser user);

    /**
     * Query unallocated user role list by condition with pagination
     * 
     * @param user User information
     * @return Collection of user information
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
     * Add user information
     * 
     * @param user User information
     * @return Result
     */
    public int insertUser(SysUser user);

    /**
     * Modify user information
     * 
     * @param user User information
     * @return Result
     */
    public int updateUser(SysUser user);

    /**
     * Modify user avatar
     * 
     * @param userName Username
     * @param avatar Avatar URL
     * @return Result
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * Reset user password
     * 
     * @param userName Username
     * @param password Password
     * @return Result
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

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
     * Check if username is unique
     * 
     * @param userName Username
     * @return Result
     */
    public SysUser checkUserNameUnique(String userName);

    /**
     * Check if phone number is unique
     *
     * @param phonenumber Phone number
     * @return Result
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * Check if email is unique
     *
     * @param email User email
     * @return Result
     */
    public SysUser checkEmailUnique(String email);
}
