package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.api.domain.SysDictData;
import com.ruoyi.system.api.domain.SysDictType;

/**
 * Dictionary Business Layer
 * 
 * @author ruoyi
 */
public interface ISysDictTypeService
{
    /**
     * Query dictionary types by condition with pagination
     * 
     * @param dictType Dictionary type information
     * @return Collection of dictionary type information
     */
    public List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * Query all dictionary types
     * 
     * @return Collection of dictionary type information
     */
    public List<SysDictType> selectDictTypeAll();

    /**
     * Query dictionary data by dictionary type
     * 
     * @param dictType Dictionary type
     * @return Collection of dictionary data information
     */
    public List<SysDictData> selectDictDataByType(String dictType);

    /**
     * Query information by dictionary type ID
     * 
     * @param dictId Dictionary type ID
     * @return Dictionary type
     */
    public SysDictType selectDictTypeById(Long dictId);

    /**
     * Query information by dictionary type
     * 
     * @param dictType Dictionary type
     * @return Dictionary type
     */
    public SysDictType selectDictTypeByType(String dictType);

    /**
     * Batch delete dictionary information
     * 
     * @param dictIds Dictionary IDs to be deleted
     */
    public void deleteDictTypeByIds(Long[] dictIds);

    /**
     * Load dictionary cache data
     */
    public void loadingDictCache();

    /**
     * Clear dictionary cache data
     */
    public void clearDictCache();

    /**
     * Reset dictionary cache data
     */
    public void resetDictCache();

    /**
     * Add and save dictionary type information
     * 
     * @param dictType Dictionary type information
     * @return Result
     */
    public int insertDictType(SysDictType dictType);

    /**
     * Modify and save dictionary type information
     * 
     * @param dictType Dictionary type information
     * @return Result
     */
    public int updateDictType(SysDictType dictType);

    /**
     * Check if dictionary type name is unique
     * 
     * @param dictType Dictionary type
     * @return Result
     */
    public boolean checkDictTypeUnique(SysDictType dictType);
}
