package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.service.ISysPostService;

/**
 * Position Information Service Layer Processing
 * 
 * @author ruoyi
 */
@Service
public class SysPostServiceImpl implements ISysPostService
{
    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    /**
     * Query position information collection
     * 
     * @param post Position information
     * @return Position information collection
     */
    @Override
    public List<SysPost> selectPostList(SysPost post)
    {
        return postMapper.selectPostList(post);
    }

    /**
     * Query all positions
     * 
     * @return Position list
     */
    @Override
    public List<SysPost> selectPostAll()
    {
        return postMapper.selectPostAll();
    }

    /**
     * Query position information by position ID
     * 
     * @param postId Position ID
     * @return Role object information
     */
    @Override
    public SysPost selectPostById(Long postId)
    {
        return postMapper.selectPostById(postId);
    }

    /**
     * Get position selection list by user ID
     * 
     * @param userId User ID
     * @return Selected position ID list
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId)
    {
        return postMapper.selectPostListByUserId(userId);
    }

    /**
     * Check if position name is unique
     * 
     * @param post Position information
     * @return Result
     */
    @Override
    public boolean checkPostNameUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * Check if position code is unique
     * 
     * @param post Position information
     * @return Result
     */
    @Override
    public boolean checkPostCodeUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * Query position usage count by position ID
     * 
     * @param postId Position ID
     * @return Result
     */
    @Override
    public int countUserPostById(Long postId)
    {
        return userPostMapper.countUserPostById(postId);
    }

    /**
     * Delete position information
     * 
     * @param postId Position ID
     * @return Result
     */
    @Override
    public int deletePostById(Long postId)
    {
        return postMapper.deletePostById(postId);
    }

    /**
     * Batch delete position information
     * 
     * @param postIds Position IDs to be deleted
     * @return Result
     */
    @Override
    public int deletePostByIds(Long[] postIds)
    {
        for (Long postId : postIds)
        {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0)
            {
                throw new ServiceException(String.format("%1$s has been assigned and cannot be deleted", post.getPostName()));
            }
        }
        return postMapper.deletePostByIds(postIds);
    }

    /**
     * Add and save position information
     * 
     * @param post Position information
     * @return Result
     */
    @Override
    public int insertPost(SysPost post)
    {
        return postMapper.insertPost(post);
    }

    /**
     * Modify and save position information
     * 
     * @param post Position information
     * @return Result
     */
    @Override
    public int updatePost(SysPost post)
    {
        return postMapper.updatePost(post);
    }
}
