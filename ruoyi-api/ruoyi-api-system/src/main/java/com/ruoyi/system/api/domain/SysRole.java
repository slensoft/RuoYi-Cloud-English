package com.ruoyi.system.api.domain;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.annotation.Excel.ColumnType;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * Role table sys_role
 *
 * @author ruoyi
 */
public class SysRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Role ID */
    @Excel(name = "Role ID", cellType = ColumnType.NUMERIC)
    private Long roleId;

    /** Role name */
    @Excel(name = "Role name")
    private String roleName;

    /** Role permission */
    @Excel(name = "Role permission")
    private String roleKey;

    /** Role sort */
    @Excel(name = "Role sort")
    private Integer roleSort;

    /** Data scope (1: all data permissions; 2: custom data permissions; 3: department data permissions; 4: department and below data permissions; 5: only personal data permissions) */
    @Excel(name = "Data scope", readConverterExp = "1=all data permissions,2=custom data permissions,3=department data permissions,4=department and below data permissions,5=only personal data permissions")
    private String dataScope;

    /** Whether the menu tree selection item is associated with display (0: parent and child are not associated with display 1: parent and child are associated with display) */
    private boolean menuCheckStrictly;

    /** Whether the department tree selection item is associated with display (0: parent and child are not associated with display 1: parent and child are associated with display) */
    private boolean deptCheckStrictly;

    /** Role status (0 normal 1 disabled) */
    @Excel(name = "Role status", readConverterExp = "0=normal,1=disabled")
    private String status;

    /** Delete flag (0 represents existence 2 represents deletion) */
    private String delFlag;

    /** Whether the user has this role flag Default does not exist */
    private boolean flag = false;

    /** Menu group */
    private Long[] menuIds;

    /** Department group (data permissions) */
    private Long[] deptIds;

    /** Role menu permissions */
    private Set<String> permissions;

    public SysRole()
    {

    }

    public SysRole(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(Long roleId)
    {
        return roleId != null && 1L == roleId;
    }

    @NotBlank(message = "role name cannot be empty")
    @Size(min = 0, max = 30, message = "role name length cannot exceed 30 characters")
    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @NotBlank(message = "permission characters cannot be empty")
    @Size(min = 0, max = 100, message = "permission character length cannot exceed 100 characters")
    public String getRoleKey()
    {
        return roleKey;
    }

    public void setRoleKey(String roleKey)
    {
        this.roleKey = roleKey;
    }

    @NotNull(message = "display order cannot be empty")
    public Integer getRoleSort()
    {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort)
    {
        this.roleSort = roleSort;
    }

    public String getDataScope()
    {
        return dataScope;
    }

    public void setDataScope(String dataScope)
    {
        this.dataScope = dataScope;
    }

    public boolean isMenuCheckStrictly()
    {
        return menuCheckStrictly;
    }

    public void setMenuCheckStrictly(boolean menuCheckStrictly)
    {
        this.menuCheckStrictly = menuCheckStrictly;
    }

    public boolean isDeptCheckStrictly()
    {
        return deptCheckStrictly;
    }

    public void setDeptCheckStrictly(boolean deptCheckStrictly)
    {
        this.deptCheckStrictly = deptCheckStrictly;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public Long[] getMenuIds()
    {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds)
    {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds()
    {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds)
    {
        this.deptIds = deptIds;
    }

    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("roleKey", getRoleKey())
            .append("roleSort", getRoleSort())
            .append("dataScope", getDataScope())
            .append("menuCheckStrictly", isMenuCheckStrictly())
            .append("deptCheckStrictly", isDeptCheckStrictly())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
