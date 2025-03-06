package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPost;

/**
 * Position Information Service Layer
 * 
 * @author ruoyi
 */
public interface ISysPostService
{
    /**
     * Query position information collection
     * 
     * @param post Position information
     * @return Position list
     */
    public List<SysPost> selectPostList(SysPost post);

    /**
     * Query all positions
     * 
     * @return Position list
     */
    public List<SysPost> selectPostAll();

    /**
     * Query position information by position ID
     * 
     * @param postId Position ID
     * @return Role object information
     */
    public SysPost selectPostById(Long postId);

    /**
     * Get position selection list by user ID
     * 
     * @param userId User ID
     * @return Selected position ID list
     */
    public List<Long> selectPostListByUserId(Long userId);

    /**
     * Validate position name
     * 
     * @param post Position information
     * @return Result
     */
    public boolean checkPostNameUnique(SysPost post);

    /**
     * Validate position code
     * 
     * @param post Position information
     * @return Result
     */
    public boolean checkPostCodeUnique(SysPost post);

    /**
     * Query position usage count by position ID
     * 
     * @param postId Position ID
     * @return Result
     */
    public int countUserPostById(Long postId);

    /**
     * Delete position information
     * 
     * @param postId Position ID
     * @return Result
     */
    public int deletePostById(Long postId);

    /**
     * Batch delete position information
     * 
     * @param postIds Position IDs to be deleted
     * @return Result
     */
    public int deletePostByIds(Long[] postIds);

    /**
     * Add and save position information
     * 
     * @param post Position information
     * @return Result
     */
    public int insertPost(SysPost post);

    /**
     * Modify and save position information
     * 
     * @param post Position information
     * @return Result
     */
    public int updatePost(SysPost post);
}
