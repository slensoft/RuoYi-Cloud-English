package com.ruoyi.file.service;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.nacos.common.utils.IoUtils;
import com.ruoyi.file.config.MinioConfig;
import com.ruoyi.file.utils.FileUploadUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

/**
 * Minio file storage
 *
 * @author ruoyi
 */
@Service
public class MinioSysFileServiceImpl implements ISysFileService
{
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    /**
     * Minio file upload interface
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
            String fileName = FileUploadUtils.extractFilename(file);
            inputStream = file.getInputStream();
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            client.putObject(args);
            return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Minio Failed to upload file", e);
        }
        finally
        {
            IoUtils.closeQuietly(inputStream);
        }
    }
}
