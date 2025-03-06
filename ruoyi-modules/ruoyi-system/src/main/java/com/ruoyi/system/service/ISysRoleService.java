package com.ruoyi.system.service;

import java.util.List;
import java.util.Set;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.domain.SysUserRole;

/**
 * Role Business Layer
 * 
 * @author ruoyi
 */
public interface ISysRoleService
{
    /**
     * Query role data by pagination based on conditions
     * 
     * @param role Role information
     * @return Role data collection information
     */
    public List<SysRole> selectRoleList(SysRole role);

    /**
     * Query role list by user ID
     * 
     * @param userId User ID
     * @return Role list
     */
    public List<SysRole> selectRolesByUserId(Long userId);

    /**
     * Query role permissions by user ID
     * 
     * @param userId User ID
     * @return Permission list
     */
    public Set<String> selectRolePermissionByUserId(Long userId);

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
     * Check if role name is unique
     * 
     * @param role Role information
     * @return Result
     */
    public boolean checkRoleNameUnique(SysRole role);

    /**
     * Check if role permission is unique
     * 
     * @param role Role information
     * @return Result
     */
    public boolean checkRoleKeyUnique(SysRole role);

    /**
     * Check if role operation is allowed
     * 
     * @param role Role information
     */
    public void checkRoleAllowed(SysRole role);

    /**
     * Check if role has data permissions
     * 
     * @param roleIds Role IDs
     */
    public void checkRoleDataScope(Long... roleIds);

    /**
     * Query role usage count by role ID
     * 
     * @param roleId Role ID
     * @return Result
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * Add and save role information
     * 
     * @param role Role information
     * @return Result
     */
    public int insertRole(SysRole role);

    /**
     * Modify and save role information
     * 
     * @param role Role information
     * @return Result
     */
    public int updateRole(SysRole role);

    /**
     * Modify role status
     * 
     * @param role Role information
     * @return Result
     */
    public int updateRoleStatus(SysRole role);

    /**
     * Modify data permission information
     * 
     * @param role Role information
     * @return Result
     */
    public int authDataScope(SysRole role);

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

    /**
     * Cancel user role authorization
     * 
     * @param userRole User and role association information
     * @return Result
     */
    public int deleteAuthUser(SysUserRole userRole);

    /**
     * Batch cancel user role authorization
     * 
     * @param roleId Role ID
     * @param userIds User IDs to cancel authorization
     * @return Result
     */
    public int deleteAuthUsers(Long roleId, Long[] userIds);

    /**
     * Batch select and authorize user roles
     * 
     * @param roleId Role ID
     * @param userIds User IDs to be deleted
     * @return Result
     */
    public int insertAuthUsers(Long roleId, Long[] userIds);
}
