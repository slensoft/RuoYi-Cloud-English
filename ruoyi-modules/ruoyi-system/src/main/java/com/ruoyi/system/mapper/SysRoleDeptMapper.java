package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysRoleDept;

/**
 * Role and Department Association Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysRoleDeptMapper
{
    /**
     * Delete role and department association by role ID
     * 
     * @param roleId Role ID
     * @return Result
     */
    public int deleteRoleDeptByRoleId(Long roleId);

    /**
     * Batch delete role department association information
     * 
     * @param ids Data IDs to be deleted
     * @return Result
     */
    public int deleteRoleDept(Long[] ids);

    /**
     * Query department usage count
     * 
     * @param deptId Department ID
     * @return Result
     */
    public int selectCountRoleDeptByDeptId(Long deptId);

    /**
     * Batch add role department information
     * 
     * @param roleDeptList Role department list
     * @return Result
     */
    public int batchRoleDept(List<SysRoleDept> roleDeptList);
}
