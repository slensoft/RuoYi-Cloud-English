package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysRoleMenu;

/**
 * Role and Menu Association Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysRoleMenuMapper
{
    /**
     * Query menu usage count
     * 
     * @param menuId Menu ID
     * @return Result
     */
    public int checkMenuExistRole(Long menuId);

    /**
     * Delete role and menu association by role ID
     * 
     * @param roleId Role ID
     * @return Result
     */
    public int deleteRoleMenuByRoleId(Long roleId);

    /**
     * Batch delete role menu association information
     * 
     * @param ids Data IDs to be deleted
     * @return Result
     */
    public int deleteRoleMenu(Long[] ids);

    /**
     * Batch add role menu information
     * 
     * @param roleMenuList Role menu list
     * @return Result
     */
    public int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
