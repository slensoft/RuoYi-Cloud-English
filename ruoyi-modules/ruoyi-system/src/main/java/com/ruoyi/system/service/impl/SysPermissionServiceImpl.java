package com.ruoyi.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysPermissionService;
import com.ruoyi.system.service.ISysRoleService;

/**
 * User Permission Processing
 * 
 * @author ruoyi
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService
{
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * Get role data permissions
     * 
     * @param userId User ID
     * @return Role permission information
     */
    @Override
    public Set<String> getRolePermission(SysUser user)
    {
        Set<String> roles = new HashSet<String>();
        // Administrator has all permissions
        if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * Get menu data permissions
     * 
     * @param userId User ID
     * @return Menu permission information
     */
    @Override
    public Set<String> getMenuPermission(SysUser user)
    {
        Set<String> perms = new HashSet<String>();
        // Administrator has all permissions
        if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            List<SysRole> roles = user.getRoles();
            if (!CollectionUtils.isEmpty(roles))
            {
                // Set permissions property for multiple roles to match data permissions
                for (SysRole role : roles)
                {
                    if (StringUtils.equals(role.getStatus(), UserConstants.ROLE_NORMAL))
                    {
                        Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
                        role.setPermissions(rolePerms);
                        perms.addAll(rolePerms);
                    }
                }
            }
            else
            {
                perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
            }
        }
        return perms;
    }
}
