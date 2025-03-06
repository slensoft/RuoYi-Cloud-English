package com.ruoyi.system.service;

import java.util.List;
import java.util.Set;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.domain.vo.TreeSelect;

/**
 * Menu Business Layer
 * 
 * @author ruoyi
 */
public interface ISysMenuService
{
    /**
     * Query system menu list by user
     * 
     * @param userId User ID
     * @return Menu list
     */
    public List<SysMenu> selectMenuList(Long userId);

    /**
     * Query system menu list by user
     * 
     * @param menu Menu information
     * @param userId User ID
     * @return Menu list
     */
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    /**
     * Query permissions by user ID
     * 
     * @param userId User ID
     * @return Permission list
     */
    public Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * Query permissions by role ID
     * 
     * @param roleId Role ID
     * @return Permission list
     */
    public Set<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * Query menu tree information by user ID
     * 
     * @param userId User ID
     * @return Menu list
     */
    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * Query menu tree information by role ID
     * 
     * @param roleId Role ID
     * @return Selected menu list
     */
    public List<Long> selectMenuListByRoleId(Long roleId);

    /**
     * Build menus required for frontend routing
     * 
     * @param menus Menu list
     * @return Route list
     */
    public List<RouterVo> buildMenus(List<SysMenu> menus);

    /**
     * Build tree structure required by frontend
     * 
     * @param menus Menu list
     * @return Tree structure list
     */
    public List<SysMenu> buildMenuTree(List<SysMenu> menus);

    /**
     * Build dropdown tree structure required by frontend
     * 
     * @param menus Menu list
     * @return Dropdown tree structure list
     */
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    /**
     * Query information by menu ID
     * 
     * @param menuId Menu ID
     * @return Menu information
     */
    public SysMenu selectMenuById(Long menuId);

    /**
     * Check if menu child nodes exist
     * 
     * @param menuId Menu ID
     * @return Result true exists false not exists
     */
    public boolean hasChildByMenuId(Long menuId);

    /**
     * Check if menu exists in role
     * 
     * @param menuId Menu ID
     * @return Result true exists false not exists
     */
    public boolean checkMenuExistRole(Long menuId);

    /**
     * Add and save menu information
     * 
     * @param menu Menu information
     * @return Result
     */
    public int insertMenu(SysMenu menu);

    /**
     * Modify and save menu information
     * 
     * @param menu Menu information
     * @return Result
     */
    public int updateMenu(SysMenu menu);

    /**
     * Delete menu management information
     * 
     * @param menuId Menu ID
     * @return Result
     */
    public int deleteMenuById(Long menuId);

    /**
     * Check if menu name is unique
     * 
     * @param menu Menu information
     * @return Result
     */
    public boolean checkMenuNameUnique(SysMenu menu);
}
