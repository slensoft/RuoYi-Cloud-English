package com.ruoyi.common.core.utils.file;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Image Processing Utility Class
 *
 * @author ruoyi
 */
public class ImageUtils
{
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static byte[] getImage(String imagePath)
    {
        InputStream is = getFile(imagePath);
        try
        {
            return IOUtils.toByteArray(is);
        }
        catch (Exception e)
        {
            log.error("Image loading exception {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath)
    {
        try
        {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        }
        catch (Exception e)
        {
            log.error("Error getting image {}", e);
        }
        return null;
    }

    /**
     * Read file as byte data
     * 
     * @param url URL
     * @return Byte data
     */
    public static byte[] readFile(String url)
    {
        InputStream in = null;
        try
        {
            // Network address
            URL urlObj = new URL(url);
            URLConnection urlConnection = urlObj.openConnection();
            urlConnection.setConnectTimeout(30 * 1000);
            urlConnection.setReadTimeout(60 * 1000);
            urlConnection.setDoInput(true);
            in = urlConnection.getInputStream();
            return IOUtils.toByteArray(in);
        }
        catch (Exception e)
        {
            log.error("File access exception {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
    }
}
