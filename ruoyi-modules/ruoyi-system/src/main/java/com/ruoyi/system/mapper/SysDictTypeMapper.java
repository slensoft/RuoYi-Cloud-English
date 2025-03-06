package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.api.domain.SysDictType;

/**
 * Dictionary Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysDictTypeMapper
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
     * Delete dictionary information by dictionary ID
     * 
     * @param dictId Dictionary ID
     * @return Result
     */
    public int deleteDictTypeById(Long dictId);

    /**
     * Batch delete dictionary type information
     * 
     * @param dictIds Dictionary IDs to be deleted
     * @return Result
     */
    public int deleteDictTypeByIds(Long[] dictIds);

    /**
     * Add dictionary type information
     * 
     * @param dictType Dictionary type information
     * @return Result
     */
    public int insertDictType(SysDictType dictType);

    /**
     * Modify dictionary type information
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
    public SysDictType checkDictTypeUnique(String dictType);
}
