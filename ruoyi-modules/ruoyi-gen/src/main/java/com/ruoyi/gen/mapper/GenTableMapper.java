package com.ruoyi.gen.mapper;

import java.util.List;
import com.ruoyi.gen.domain.GenTable;

/**
 * Business data layer
 *
 * @author ruoyi
 */
public interface GenTableMapper
{
    /**
     * Query business list
     *
     * @param genTable Business information
     * @return Business collection
     */
    public List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * Query database list
     *
     * @param genTable Business information
     * @return Database table collection
     */
    public List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * Query database list
     *
     * @param tableNames Table name group
     * @return Database table collection
     */
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * Query all table information
     *
     * @return Table information collection
     */
    public List<GenTable> selectGenTableAll();

    /**
     * Query table ID business information
     *
     * @param id Business ID
     * @return Business information
     */
    public GenTable selectGenTableById(Long id);

    /**
     * Query table name business information
     *
     * @param tableName Table name
     * @return Business information
     */
    public GenTable selectGenTableByName(String tableName);

    /**
     * Add business
     *
     * @param genTable Business information
     * @return Result
     */
    public int insertGenTable(GenTable genTable);

    /**
     * Modify business
     *
     * @param genTable Business information
     * @return Result
     */
    public int updateGenTable(GenTable genTable);

    /**
     * Batch delete business
     *
     * @param ids IDs of data to be deleted
     * @return Result
     */
    public int deleteGenTableByIds(Long[] ids);
}
