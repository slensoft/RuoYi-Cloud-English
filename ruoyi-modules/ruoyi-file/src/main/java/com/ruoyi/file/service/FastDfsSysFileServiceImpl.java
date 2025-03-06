package com.ruoyi.file.service;

import java.io.InputStream;
import com.alibaba.nacos.common.utils.IoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ruoyi.common.core.utils.file.FileTypeUtils;

/**
 * FastDFS file storage
 *
 * @author ruoyi
 */
@Service
public class FastDfsSysFileServiceImpl implements ISysFileService
{
    /**
     * Domain or local access address
     */
    @Value("${fdfs.domain}")
    public String domain;

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * FastDfs file upload interface
     *
     * @param file Uploaded file
     * @return Access address
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        InputStream inputStream = null;
        try
        {
            inputStream = file.getInputStream();
            StorePath storePath = storageClient.uploadFile(inputStream, file.getSize(), FileTypeUtils.getExtension(file), null);
            return domain + "/" + storePath.getFullPath();
        }
        catch (Exception e)
        {
            throw new RuntimeException("FastDfs Failed to upload file", e);
        }
        finally
        {
            IoUtils.closeQuietly(inputStream);
        }
    }
}
