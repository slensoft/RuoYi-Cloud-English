package com.ruoyi.gen.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.gen.domain.GenTableColumn;
import com.ruoyi.gen.mapper.GenTableColumnMapper;

/**
 * Business field service layer implementation
 *
 * @author ruoyi
 */
@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService
{
	@Autowired
	private GenTableColumnMapper genTableColumnMapper;

	/**
     * Query business field list
     *
     * @param tableId Business field ID
     * @return Business field collection
     */
	@Override
	public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId)
	{
	    return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
	}

    /**
     * Add business field
     *
     * @param genTableColumn Business field information
     * @return Result
     */
	@Override
	public int insertGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.insertGenTableColumn(genTableColumn);
	}

	/**
     * Modify business field
     *
     * @param genTableColumn Business field information
     * @return Result
     */
	@Override
	public int updateGenTableColumn(GenTableColumn genTableColumn)
	{
	    return genTableColumnMapper.updateGenTableColumn(genTableColumn);
	}

	/**
     * Delete business field object
     *
     * @param ids IDs of data to be deleted
     * @return Result
     */
	@Override
	public int deleteGenTableColumnByIds(String ids)
	{
		return genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
	}
}
