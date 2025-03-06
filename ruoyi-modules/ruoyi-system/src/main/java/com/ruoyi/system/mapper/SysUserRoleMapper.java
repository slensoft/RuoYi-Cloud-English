package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SysUserRole;

/**
 * User and Role Association Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysUserRoleMapper
{
    /**
     * Delete user and role association by user ID
     * 
     * @param userId User ID
     * @return Result
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * Batch delete user and role associations
     * 
     * @param ids Data IDs to be deleted
     * @return Result
     */
    public int deleteUserRole(Long[] ids);

    /**
     * Query role usage count by role ID
     * 
     * @param roleId Role ID
     * @return Result
     */
    public int countUserRoleByRoleId(Long roleId);

    /**
     * Batch add user role information
     * 
     * @param userRoleList User role list
     * @return Result
     */
    public int batchUserRole(List<SysUserRole> userRoleList);

    /**
     * Delete user and role association information
     * 
     * @param userRole User and role association information
     * @return Result
     */
    public int deleteUserRoleInfo(SysUserRole userRole);

    /**
     * Batch revoke user role authorization
     * 
     * @param roleId Role ID
     * @param userIds User data IDs to be deleted
     * @return Result
     */
    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}
