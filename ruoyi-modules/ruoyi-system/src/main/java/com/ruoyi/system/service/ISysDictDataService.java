package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.api.domain.SysDictData;

/**
 * Dictionary Business Layer
 * 
 * @author ruoyi
 */
public interface ISysDictDataService
{
    /**
     * Query dictionary data by condition with pagination
     * 
     * @param dictData Dictionary data information
     * @return Collection of dictionary data information
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * Query dictionary data information by dictionary type and dictionary value
     * 
     * @param dictType Dictionary type
     * @param dictValue Dictionary value
     * @return Dictionary label
     */
    public String selectDictLabel(String dictType, String dictValue);

    /**
     * Query information by dictionary data ID
     * 
     * @param dictCode Dictionary data ID
     * @return Dictionary data
     */
    public SysDictData selectDictDataById(Long dictCode);

    /**
     * Batch delete dictionary data information
     * 
     * @param dictCodes Dictionary data IDs to be deleted
     */
    public void deleteDictDataByIds(Long[] dictCodes);

    /**
     * Add and save dictionary data information
     * 
     * @param dictData Dictionary data information
     * @return Result
     */
    public int insertDictData(SysDictData dictData);

    /**
     * Modify and save dictionary data information
     * 
     * @param dictData Dictionary data information
     * @return Result
     */
    public int updateDictData(SysDictData dictData);
}
