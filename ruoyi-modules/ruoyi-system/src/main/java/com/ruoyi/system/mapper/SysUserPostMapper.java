package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserPost;

/**
 * User and Post Association Table Data Layer
 * 
 * @author ruoyi
 */
public interface SysUserPostMapper
{
    /**
     * Delete user and post association by user ID
     * 
     * @param userId User ID
     * @return Result
     */
    public int deleteUserPostByUserId(Long userId);

    /**
     * Query post usage count by post ID
     * 
     * @param postId Post ID
     * @return Result
     */
    public int countUserPostById(Long postId);

    /**
     * Batch delete user and post associations
     * 
     * @param ids Data IDs to be deleted
     * @return Result
     */
    public int deleteUserPost(Long[] ids);

    /**
     * Batch add user post information
     * 
     * @param userPostList User post list
     * @return Result
     */
    public int batchUserPost(List<SysUserPost> userPostList);
}
