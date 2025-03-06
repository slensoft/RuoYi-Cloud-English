package com.ruoyi.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * File upload interface
 * 
 * @author ruoyi
 */
public interface ISysFileService
{
    /**
     * File upload interface
     * 
     * @param file Uploaded file
     * @return Access address
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;
}
