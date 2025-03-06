package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysNotice;

/**
 * Notice Service Layer
 * 
 * @author ruoyi
 */
public interface ISysNoticeService
{
    /**
     * Query notice information
     * 
     * @param noticeId Notice ID
     * @return Notice information
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * Query notice list
     * 
     * @param notice Notice information
     * @return Notice collection
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * Add notice
     * 
     * @param notice Notice information
     * @return Result
     */
    public int insertNotice(SysNotice notice);

    /**
     * Modify notice
     * 
     * @param notice Notice information
     * @return Result
     */
    public int updateNotice(SysNotice notice);

    /**
     * Delete notice information
     * 
     * @param noticeId Notice ID
     * @return Result
     */
    public int deleteNoticeById(Long noticeId);
    
    /**
     * Batch delete notice information
     * 
     * @param noticeIds Notice IDs to be deleted
     * @return Result
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}
