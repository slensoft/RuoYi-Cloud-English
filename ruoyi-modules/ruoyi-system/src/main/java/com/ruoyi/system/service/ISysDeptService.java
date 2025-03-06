package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.api.domain.SysDept;
import com.ruoyi.system.domain.vo.TreeSelect;

/**
 * Department Management Service Layer
 * 
 * @author ruoyi
 */
public interface ISysDeptService
{
    /**
     * Query department management data
     * 
     * @param dept Department information
     * @return Department information collection
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * Query department tree structure information
     * 
     * @param dept Department information
     * @return Department tree information collection
     */
    public List<TreeSelect> selectDeptTreeList(SysDept dept);

    /**
     * Build tree structure required by frontend
     * 
     * @param depts Department list
     * @return Tree structure list
     */
    public List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * Build dropdown tree structure required by frontend
     * 
     * @param depts Department list
     * @return Dropdown tree structure list
     */
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * Query department tree information by role ID
     * 
     * @param roleId Role ID
     * @return Selected department list
     */
    public List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * Query information by department ID
     * 
     * @param deptId Department ID
     * @return Department information
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * Query all child departments by ID (normal status)
     * 
     * @param deptId Department ID
     * @return Number of child departments
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * Check if child nodes exist in department
     * 
     * @param deptId Department ID
     * @return Result
     */
    public boolean hasChildByDeptId(Long deptId);

    /**
     * Check if users exist in department
     * 
     * @param deptId Department ID
     * @return Result true exists false not exists
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * Check if department name is unique
     * 
     * @param dept Department information
     * @return Result
     */
    public boolean checkDeptNameUnique(SysDept dept);

    /**
     * Check if department has data permissions
     * 
     * @param deptId Department ID
     */
    public void checkDeptDataScope(Long deptId);

    /**
     * Add and save department information
     * 
     * @param dept Department information
     * @return Result
     */
    public int insertDept(SysDept dept);

    /**
     * Modify and save department information
     * 
     * @param dept Department information
     * @return Result
     */
    public int updateDept(SysDept dept);

    /**
     * Delete department management information
     * 
     * @param deptId Department ID
     * @return Result
     */
    public int deleteDeptById(Long deptId);
}
