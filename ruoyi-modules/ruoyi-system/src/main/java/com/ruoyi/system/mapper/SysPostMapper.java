package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPost;

/**
 * Post Information Data Layer
 * 
 * @author ruoyi
 */
public interface SysPostMapper
{
    /**
     * Query post data collection
     * 
     * @param post Post information
     * @return Collection of post data
     */
    public List<SysPost> selectPostList(SysPost post);

    /**
     * Query all posts
     * 
     * @return Post list
     */
    public List<SysPost> selectPostAll();

    /**
     * Query post information by post ID
     * 
     * @param postId Post ID
     * @return Post object information
     */
    public SysPost selectPostById(Long postId);

    /**
     * Get post selection box list by user ID
     * 
     * @param userId User ID
     * @return Selected post ID list
     */
    public List<Long> selectPostListByUserId(Long userId);

    /**
     * Query user's post group
     * 
     * @param userName Username
     * @return Result
     */
    public List<SysPost> selectPostsByUserName(String userName);

    /**
     * Delete post information
     * 
     * @param postId Post ID
     * @return Result
     */
    public int deletePostById(Long postId);

    /**
     * Batch delete post information
     * 
     * @param postIds Post IDs to be deleted
     * @return Result
     */
    public int deletePostByIds(Long[] postIds);

    /**
     * Modify post information
     * 
     * @param post Post information
     * @return Result
     */
    public int updatePost(SysPost post);

    /**
     * Add post information
     * 
     * @param post Post information
     * @return Result
     */
    public int insertPost(SysPost post);

    /**
     * Check post name
     * 
     * @param postName Post name
     * @return Result
     */
    public SysPost checkPostNameUnique(String postName);

    /**
     * Check post code
     * 
     * @param postCode Post code
     * @return Result
     */
    public SysPost checkPostCodeUnique(String postCode);
}
