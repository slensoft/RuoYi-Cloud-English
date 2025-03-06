package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.api.domain.SysRole;

/**
 * Role Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysRoleMapper
{
    /**
     * Query role data by condition with pagination
     * 
     * @param role Role information
     * @return Collection of role data information
     */
    public List<SysRole> selectRoleList(SysRole role);

    /**
     * Query roles by user ID
     * 
     * @param userId User ID
     * @return Role list
     */
    public List<SysRole> selectRolePermissionByUserId(Long userId);

    /**
     * Query all roles
     * 
     * @return Role list
     */
    public List<SysRole> selectRoleAll();

    /**
     * Get role selection box list by user ID
     * 
     * @param userId User ID
     * @return Selected role ID list
     */
    public List<Long> selectRoleListByUserId(Long userId);

    /**
     * Query role by role ID
     * 
     * @param roleId Role ID
     * @return Role object information
     */
    public SysRole selectRoleById(Long roleId);

    /**
     * Query roles by username
     * 
     * @param userName Username
     * @return Role list
     */
    public List<SysRole> selectRolesByUserName(String userName);

    /**
     * Check if role name is unique
     * 
     * @param roleName Role name
     * @return Role information
     */
    public SysRole checkRoleNameUnique(String roleName);

    /**
     * Check if role permission is unique
     * 
     * @param roleKey Role permission
     * @return Role information
     */
    public SysRole checkRoleKeyUnique(String roleKey);

    /**
     * Modify role information
     * 
     * @param role Role information
     * @return Result
     */
    public int updateRole(SysRole role);

    /**
     * Add role information
     * 
     * @param role Role information
     * @return Result
     */
    public int insertRole(SysRole role);

    /**
     * Delete role by role ID
     * 
     * @param roleId Role ID
     * @return Result
     */
    public int deleteRoleById(Long roleId);

    /**
     * Batch delete role information
     * 
     * @param roleIds Role IDs to be deleted
     * @return Result
     */
    public int deleteRoleByIds(Long[] roleIds);
}
