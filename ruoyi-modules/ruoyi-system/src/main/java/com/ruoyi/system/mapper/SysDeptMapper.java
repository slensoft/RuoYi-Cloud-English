package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.api.domain.SysDept;

/**
 * Department Management Data Layer
 * 
 * @author ruoyi
 */
public interface SysDeptMapper
{
    /**
     * Query department management data
     * 
     * @param dept Department information
     * @return Department information collection
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * Query department tree information by role ID
     * 
     * @param roleId Role ID
     * @param deptCheckStrictly Whether department tree selection is associated with display
     * @return Selected department list
     */
    public List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * Query information by department ID
     * 
     * @param deptId Department ID
     * @return Department information
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * Query all child departments by ID
     * 
     * @param deptId Department ID
     * @return Department list
     */
    public List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * Query all child departments by ID (normal status)
     * 
     * @param deptId Department ID
     * @return Number of child departments
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * Check if child nodes exist
     * 
     * @param deptId Department ID
     * @return Result
     */
    public int hasChildByDeptId(Long deptId);

    /**
     * Check if users exist in the department
     * 
     * @param deptId Department ID
     * @return Result
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * Check if department name is unique
     * 
     * @param deptName Department name
     * @param parentId Parent department ID
     * @return Result
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * Add department information
     * 
     * @param dept Department information
     * @return Result
     */
    public int insertDept(SysDept dept);

    /**
     * Modify department information
     * 
     * @param dept Department information
     * @return Result
     */
    public int updateDept(SysDept dept);

    /**
     * Set department status to normal
     * 
     * @param deptIds Department ID array
     */
    public void updateDeptStatusNormal(Long[] deptIds);

    /**
     * Modify child element relationships
     * 
     * @param depts Child elements
     * @return Result
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * Delete department management information
     * 
     * @param deptId Department ID
     * @return Result
     */
    public int deleteDeptById(Long deptId);
}
