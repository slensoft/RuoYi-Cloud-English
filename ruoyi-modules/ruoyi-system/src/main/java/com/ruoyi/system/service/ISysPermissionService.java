package com.ruoyi.system.service;

import java.util.Set;

import com.ruoyi.system.api.domain.SysUser;

/**
 * Permission Information Service Layer
 * 
 * @author ruoyi
 */
public interface ISysPermissionService
{
    /**
     * Get role data permissions
     * 
     * @param userId User ID
     * @return Role permission information
     */
    public Set<String> getRolePermission(SysUser user);

    /**
     * Get menu data permissions
     * 
     * @param userId User ID
     * @return Menu permission information
     */
    public Set<String> getMenuPermission(SysUser user);
}
