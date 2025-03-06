package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.SysMenu;

/**
 * Menu Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysMenuMapper
{
    /**
     * Query system menu list
     * 
     * @param menu Menu information
     * @return Menu list
     */
    public List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * Query all user permissions
     * 
     * @return Permission list
     */
    public List<String> selectMenuPerms();

    /**
     * Query system menu list by user
     * 
     * @param menu Menu information
     * @return Menu list
     */
    public List<SysMenu> selectMenuListByUserId(SysMenu menu);

    /**
     * Query permissions by role ID
     * 
     * @param roleId Role ID
     * @return Permission list
     */
    public List<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * Query permissions by user ID
     * 
     * @param userId User ID
     * @return Permission list
     */
    public List<String> selectMenuPermsByUserId(Long userId);

    /**
     * Query all menu tree
     * 
     * @return Menu list
     */
    public List<SysMenu> selectMenuTreeAll();

    /**
     * Query menu tree by user ID
     * 
     * @param userId User ID
     * @return Menu list
     */
    public List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * Query menu tree information by role ID
     * 
     * @param roleId Role ID
     * @param menuCheckStrictly Whether menu tree selection items are associated with display
     * @return Selected menu list
     */
    public List<Long> selectMenuListByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);

    /**
     * Query information by menu ID
     * 
     * @param menuId Menu ID
     * @return Menu information
     */
    public SysMenu selectMenuById(Long menuId);

    /**
     * Check if menu has child nodes
     * 
     * @param menuId Menu ID
     * @return Result
     */
    public int hasChildByMenuId(Long menuId);

    /**
     * Add menu information
     * 
     * @param menu Menu information
     * @return Result
     */
    public int insertMenu(SysMenu menu);

    /**
     * Modify menu information
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
     * @param menuName Menu name
     * @param parentId Parent menu ID
     * @return Result
     */
    public SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}
