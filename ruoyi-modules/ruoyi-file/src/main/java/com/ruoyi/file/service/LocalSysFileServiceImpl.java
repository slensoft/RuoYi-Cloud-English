package com.ruoyi.file.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.file.utils.FileUploadUtils;

/**
 * Local file storage
 * 
 * @author ruoyi
 */
@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService
{
    /**
     * Resource mapping path prefix
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * Domain or local access address
     */
    @Value("${file.domain}")
    public String domain;
    
    /**
     * Root path for storing uploaded files locally
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * Local file upload interface
     * 
     * @param file Uploaded file
     * @return Access address
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        String name = FileUploadUtils.upload(localFilePath, file);
        String url = domain + localFilePrefix + name;
        return url;
    }
}
