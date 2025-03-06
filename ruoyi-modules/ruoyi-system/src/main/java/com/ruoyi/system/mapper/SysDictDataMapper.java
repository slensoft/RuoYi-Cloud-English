package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.api.domain.SysDictData;

/**
 * Dictionary Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysDictDataMapper
{
    /**
     * Query dictionary data by condition with pagination
     * 
     * @param dictData Dictionary data information
     * @return Dictionary data collection information
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * Query dictionary data by dictionary type
     * 
     * @param dictType Dictionary type
     * @return Dictionary data collection information
     */
    public List<SysDictData> selectDictDataByType(String dictType);

    /**
     * Query dictionary data information by dictionary type and dictionary value
     * 
     * @param dictType Dictionary type
     * @param dictValue Dictionary value
     * @return Dictionary label
     */
    public String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    /**
     * Query information by dictionary data ID
     * 
     * @param dictCode Dictionary data ID
     * @return Dictionary data
     */
    public SysDictData selectDictDataById(Long dictCode);

    /**
     * Query dictionary data
     * 
     * @param dictType Dictionary type
     * @return Dictionary data
     */
    public int countDictDataByType(String dictType);

    /**
     * Delete dictionary data information by dictionary ID
     * 
     * @param dictCode Dictionary data ID
     * @return Result
     */
    public int deleteDictDataById(Long dictCode);

    /**
     * Batch delete dictionary data information
     * 
     * @param dictCodes Dictionary data IDs to be deleted
     * @return Result
     */
    public int deleteDictDataByIds(Long[] dictCodes);

    /**
     * Add dictionary data information
     * 
     * @param dictData Dictionary data information
     * @return Result
     */
    public int insertDictData(SysDictData dictData);

    /**
     * Modify dictionary data information
     * 
     * @param dictData Dictionary data information
     * @return Result
     */
    public int updateDictData(SysDictData dictData);

    /**
     * Synchronously modify dictionary type
     * 
     * @param oldDictType Old dictionary type
     * @param newDictType New dictionary type
     * @return Result
     */
    public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}
