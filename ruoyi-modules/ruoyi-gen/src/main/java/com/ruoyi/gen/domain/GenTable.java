package com.ruoyi.gen.domain;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.apache.commons.lang3.ArrayUtils;
import com.ruoyi.common.core.constant.GenConstants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * Business table gen_table
 *
 * @author ruoyi
 */
public class GenTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long tableId;

    /** Table name */
    @NotBlank(message = "Table name cannot be empty")
    private String tableName;

    /** Table description */
    @NotBlank(message = "Table description cannot be empty")
    private String tableComment;

    /** Associated parent table name */
    private String subTableName;

    /** Foreign key name associated with the parent table */
    private String subTableFkName;

    /** Entity class name (capitalized) */
    @NotBlank(message = "Entity class name cannot be empty")
    private String className;

    /** Template used ('crud' single table operation, 'tree' tree table operation, 'sub' main-sub table operation) */
    private String tplCategory;

    /** Front-end type (element-ui template element-plus template) */
    private String tplWebType;

    /** Package path for generation */
    @NotBlank(message = "Package path for generation cannot be empty")
    private String packageName;

    /** Module name for generation */
    @NotBlank(message = "Module name for generation cannot be empty")
    private String moduleName;

    /** Business name for generation */
    @NotBlank(message = "Business name for generation cannot be empty")
    private String businessName;

    /** Generate function name */
    @NotBlank(message = "The generated function name cannot be empty")
    private String functionName;

    /** Generate author */
    @NotBlank(message = "The author cannot be empty")
    private String functionAuthor;

    /** Code generation method (0: ZIP archive, 1: custom path) */
    private String genType;

    /** Generation path (default is the project path if not filled in) */
    private String genPath;

    /** Primary key information */
    private GenTableColumn pkColumn;

    /** Sub-table information */
    private GenTable subTable;

    /** Table column information */
    @Valid
    private List<GenTableColumn> columns;

    /** Other generation options */
    private String options;

    /** Tree code field */
    private String treeCode;

    /** Tree parent code field */
    private String treeParentCode;

    /** Tree name field */
    private String treeName;

    /** Parent menu ID field */
    private Long parentMenuId;

    /** Parent menu name field */
    private String parentMenuName;

    public Long getTableId()
    {
        return tableId;
    }

    public void setTableId(Long tableId)
    {
        this.tableId = tableId;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTableComment()
    {
        return tableComment;
    }

    public void setTableComment(String tableComment)
    {
        this.tableComment = tableComment;
    }

    public String getSubTableName()
    {
        return subTableName;
    }

    public void setSubTableName(String subTableName)
    {
        this.subTableName = subTableName;
    }

    public String getSubTableFkName()
    {
        return subTableFkName;
    }

    public void setSubTableFkName(String subTableFkName)
    {
        this.subTableFkName = subTableFkName;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getTplCategory()
    {
        return tplCategory;
    }

    public void setTplCategory(String tplCategory)
    {
        this.tplCategory = tplCategory;
    }

    public String getTplWebType()
    {
        return tplWebType;
    }

    public void setTplWebType(String tplWebType)
    {
        this.tplWebType = tplWebType;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public String getBusinessName()
    {
        return businessName;
    }

    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
    }

    public String getFunctionAuthor()
    {
        return functionAuthor;
    }

    public void setFunctionAuthor(String functionAuthor)
    {
        this.functionAuthor = functionAuthor;
    }

    public String getGenType()
    {
        return genType;
    }

    public void setGenType(String genType)
    {
        this.genType = genType;
    }

    public String getGenPath()
    {
        return genPath;
    }

    public void setGenPath(String genPath)
    {
        this.genPath = genPath;
    }

    public GenTableColumn getPkColumn()
    {
        return pkColumn;
    }

    public void setPkColumn(GenTableColumn pkColumn)
    {
        this.pkColumn = pkColumn;
    }

    public GenTable getSubTable()
    {
        return subTable;
    }

    public void setSubTable(GenTable subTable)
    {
        this.subTable = subTable;
    }
    public List<GenTableColumn> getColumns()
    {
        return columns;
    }

    public void setColumns(List<GenTableColumn> columns)
    {
        this.columns = columns;
    }

    public String getOptions()
    {
        return options;
    }

    public void setOptions(String options)
    {
        this.options = options;
    }

    public String getTreeCode()
    {
        return treeCode;
    }

    public void setTreeCode(String treeCode)
    {
        this.treeCode = treeCode;
    }

    public String getTreeParentCode()
    {
        return treeParentCode;
    }

    public void setTreeParentCode(String treeParentCode)
    {
        this.treeParentCode = treeParentCode;
    }

    public String getTreeName()
    {
        return treeName;
    }

    public void setTreeName(String treeName)
    {
        this.treeName = treeName;
    }

    public Long getParentMenuId()
    {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId)
    {
        this.parentMenuId = parentMenuId;
    }

    public String getParentMenuName()
    {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName)
    {
        this.parentMenuName = parentMenuName;
    }

    public boolean isSub()
    {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }
    public boolean isTree()
    {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud()
    {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory)
    {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField)
    {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField)
    {
        if (isTree(tplCategory))
        {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }
}
