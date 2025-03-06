package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.domain.SysRoleDept;
import com.ruoyi.system.domain.SysRoleMenu;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysRoleDeptMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysRoleMenuMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysRoleService;

/**
 * Role Service Layer Implementation
 * 
 * @author ruoyi
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService
{
    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    /**
     * Query role data by condition with pagination
     * 
     * @param role Role information
     * @return Role data collection information
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysRole> selectRoleList(SysRole role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * Query roles by user ID
     * 
     * @param userId User ID
     * @return Role list
     */
    @Override
    public List<SysRole> selectRolesByUserId(Long userId)
    {
        List<SysRole> userRoles = roleMapper.selectRolePermissionByUserId(userId);
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles)
        {
            for (SysRole userRole : userRoles)
            {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * Query permissions by user ID
     * 
     * @param userId User ID
     * @return Permission list
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId)
    {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * Query all roles
     * 
     * @return Role list
     */
    @Override
    public List<SysRole> selectRoleAll()
    {
        return SpringUtils.getAopProxy(this).selectRoleList(new SysRole());
    }

    /**
     * Get role selection box list by user ID
     * 
     * @param userId User ID
     * @return Selected role ID list
     */
    @Override
    public List<Long> selectRoleListByUserId(Long userId)
    {
        return roleMapper.selectRoleListByUserId(userId);
    }

    /**
     * Query role by role ID
     * 
     * @param roleId Role ID
     * @return Role object information
     */
    @Override
    public SysRole selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * Check if role name is unique
     * 
     * @param role Role information
     * @return Result
     */
    @Override
    public boolean checkRoleNameUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * Check if role permission is unique
     * 
     * @param role Role information
     * @return Result
     */
    @Override
    public boolean checkRoleKeyUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * Check if role is allowed to operate
     * 
     * @param role Role information
     */
    @Override
    public void checkRoleAllowed(SysRole role)
    {
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin())
        {
            throw new ServiceException("Operation not allowed on super administrator role");
        }
    }

    /**
     * Check if role has data permissions
     * 
     * @param roleIds Role IDs
     */
    @Override
    public void checkRoleDataScope(Long... roleIds)
    {
        if (!SysUser.isAdmin(SecurityUtils.getUserId()))
        {
            for (Long roleId : roleIds)
            {
                SysRole role = new SysRole();
                role.setRoleId(roleId);
                List<SysRole> roles = SpringUtils.getAopProxy(this).selectRoleList(role);
                if (StringUtils.isEmpty(roles))
                {
                    throw new ServiceException("No permission to access role data!");
                }
            }
        }
    }

    /**
     * Query role usage count by role ID
     * 
     * @param roleId Role ID
     * @return Result
     */
    @Override
    public int countUserRoleByRoleId(Long roleId)
    {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * Add and save role information
     * 
     * @param role Role information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRole(SysRole role)
    {
        // Add role information
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     * Modify and save role information
     * 
     * @param role Role information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(SysRole role)
    {
        // Modify role information
        roleMapper.updateRole(role);
        // Delete role and menu association
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * Modify role status
     * 
     * @param role Role information
     * @return Result
     */
    @Override
    public int updateRoleStatus(SysRole role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * Modify data scope
     * 
     * @param role Role information
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int authDataScope(SysRole role)
    {
        // Modify role information
        roleMapper.updateRole(role);
        // Delete role and department association
        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // Add role and department association
        return insertRoleDept(role);
    }

    /**
     * Add role menu information
     * 
     * @param role Role object
     */
    public int insertRoleMenu(SysRole role)
    {
        int rows = 1;
        // Add user and role association
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * Add role department information
     * 
     * @param role Role object
     */
    public int insertRoleDept(SysRole role)
    {
        int rows = 1;
        // Add role and department association
        List<SysRoleDept> list = new ArrayList<SysRoleDept>();
        for (Long deptId : role.getDeptIds())
        {
            SysRoleDept rd = new SysRoleDept();
            rd.setRoleId(role.getRoleId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0)
        {
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    /**
     * Delete role by role ID
     * 
     * @param roleId Role ID
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleById(Long roleId)
    {
        // Delete role and menu association
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
        // Delete role and department association
        roleDeptMapper.deleteRoleDeptByRoleId(roleId);
        return roleMapper.deleteRoleById(roleId);
    }

    /**
     * Batch delete role information
     * 
     * @param roleIds Role IDs to be deleted
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleByIds(Long[] roleIds)
    {
        for (Long roleId : roleIds)
        {
            checkRoleAllowed(new SysRole(roleId));
            checkRoleDataScope(roleId);
            SysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new ServiceException(String.format("%1$s has assigned users and cannot be deleted", role.getRoleName()));
            }
        }
        // Delete role and menu association
        roleMenuMapper.deleteRoleMenu(roleIds);
        // Delete role and department association
        roleDeptMapper.deleteRoleDept(roleIds);
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * Cancel user authorization
     * 
     * @param userRole User and role association information
     * @return Result
     */
    @Override
    public int deleteAuthUser(SysUserRole userRole)
    {
        return userRoleMapper.deleteUserRoleInfo(userRole);
    }

    /**
     * Batch cancel user authorization
     * 
     * @param roleId Role ID
     * @param userIds User IDs to cancel authorization
     * @return Result
     */
    @Override
    public int deleteAuthUsers(Long roleId, Long[] userIds)
    {
        return userRoleMapper.deleteUserRoleInfos(roleId, userIds);
    }

    /**
     * Batch add user authorization
     * 
     * @param roleId Role ID
     * @param userIds User IDs to authorize
     * @return Result
     */
    @Override
    public int insertAuthUsers(Long roleId, Long[] userIds)
    {
        // Add user and role association
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (Long userId : userIds)
        {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return userRoleMapper.batchUserRole(list);
    }
}
