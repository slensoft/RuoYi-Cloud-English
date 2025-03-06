package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysNotice;

/**
 * Announcement Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysNoticeMapper
{
    /**
     * Query announcement information
     * 
     * @param noticeId Announcement ID
     * @return Announcement information
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * Query announcement list
     * 
     * @param notice Announcement information
     * @return Announcement collection
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * Add announcement
     * 
     * @param notice Announcement information
     * @return Result
     */
    public int insertNotice(SysNotice notice);

    /**
     * Modify announcement
     * 
     * @param notice Announcement information
     * @return Result
     */
    public int updateNotice(SysNotice notice);

    /**
     * Delete announcement
     * 
     * @param noticeId Announcement ID
     * @return Result
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * Batch delete announcement information
     * 
     * @param noticeIds Announcement IDs to be deleted
     * @return Result
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}