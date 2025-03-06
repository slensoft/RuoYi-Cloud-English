package com.ruoyi.gen.mapper;

import java.util.List;
import com.ruoyi.gen.domain.GenTableColumn;

/**
 * Business field data layer
 *
 * @author ruoyi
 */
public interface GenTableColumnMapper
{
    /**
     * Query column information by table name
     *
     * @param tableName Table name
     * @return Column information
     */
    public List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    /**
     * Query business field list
     *
     * @param tableId Business field ID
     * @return Business field collection
     */
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

    /**
     * Add business field
     *
     * @param genTableColumn Business field information
     * @return Result
     */
    public int insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     * Modify business field
     *
     * @param genTableColumn Business field information
     * @return Result
     */
    public int updateGenTableColumn(GenTableColumn genTableColumn);

    /**
     * Delete business field
     *
     * @param genTableColumns Column data
     * @return Result
     */
    public int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

    /**
     * Batch delete business fields
     *
     * @param ids IDs of data to be deleted
     * @return Result
     */
    public int deleteGenTableColumnByIds(Long[] ids);
}
