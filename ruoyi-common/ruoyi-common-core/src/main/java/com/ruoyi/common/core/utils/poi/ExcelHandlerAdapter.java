package com.ruoyi.common.core.utils.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Excel Data Format Handler Adapter
 * 
 * @author ruoyi
 */
public interface ExcelHandlerAdapter
{
    /**
     * Format
     * 
     * @param value Cell data value
     * @param args Excel annotation args parameter array
     * @param cell Cell object
     * @param wb Workbook object
     *
     * @return Processed value
     */
    Object format(Object value, String[] args, Cell cell, Workbook wb);
}
