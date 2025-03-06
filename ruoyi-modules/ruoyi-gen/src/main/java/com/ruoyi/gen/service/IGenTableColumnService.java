package com.ruoyi.gen.service;

import java.util.List;
import com.ruoyi.gen.domain.GenTableColumn;

/**
 * Business field service layer
 * 
 * @author ruoyi
 */
public interface IGenTableColumnService
{
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
     * Delete business field information
     * 
     * @param ids IDs of data to be deleted
     * @return Result
     */
    public int deleteGenTableColumnByIds(String ids);
}
