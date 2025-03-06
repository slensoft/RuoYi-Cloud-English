package com.ruoyi.common.core.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import com.ruoyi.common.core.utils.StringUtils;

/**
 * File Processing Utility Class
 * 
 * @author ruoyi
 */
public class FileUtils
{
    /** Character constant: forward slash {@code '/'} */
    public static final char SLASH = '/';

    /** Character constant: backslash {@code '\\'} */
    public static final char BACKSLASH = '\\';

    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * Output byte array of specified file
     * 
     * @param filePath File path
     * @param os Output stream
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Delete file
     * 
     * @param filePath File
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // Path is a file and not empty then delete
        if (file.isFile() && file.exists())
        {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * File name verification
     * 
     * @param filename File name
     * @return true Normal false Illegal
     */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * Check if the file can be downloaded
     * 
     * @param resource File to be downloaded
     * @return true Normal false Illegal
     */
    public static boolean checkAllowDownload(String resource)
    {
        // Prevent directory jumping level
        if (StringUtils.contains(resource, ".."))
        {
            return false;
        }
        // Determine if it is within the allowed download file rule
        return ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource));
    }

    /**
     * Download file name re-encoding
     * 
     * @param request Request object
     * @param fileName File name
     * @return Encoded file name
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE browser
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // Firefox browser
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // Google browser
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // Other browsers
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    /**
     * Return file name
     *
     * @param filePath File
     * @return File name
     */
    public static String getName(String filePath)
    {
        if (null == filePath)
        {
            return null;
        }
        int len = filePath.length();
        if (0 == len)
        {
            return filePath;
        }
        if (isFileSeparator(filePath.charAt(len - 1)))
        {
            // Remove the ending separator if it ends with a separator
            len--;
        }

        int begin = 0;
        char c;
        for (int i = len - 1; i > -1; i--)
        {
            c = filePath.charAt(i);
            if (isFileSeparator(c))
            {
                // Find the last path separator (/ or \)
                begin = i + 1;
                break;
            }
        }

        return filePath.substring(begin, len);
    }

    /**
     * Whether it is a Windows or Linux (Unix) file separator<br>
     * Windows platform separator is \, Linux (Unix) is /
     *
     * @param c Character
     * @return Whether it is a Windows or Linux (Unix) file separator
     */
    public static boolean isFileSeparator(char c)
    {
        return SLASH == c || BACKSLASH == c;
    }

    /**
     * Download file name re-encoding
     *
     * @param response Response object
     * @param realFileName Real file name
     * @return
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException
    {
        String percentEncodedFileName = percentEncode(realFileName);

        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
                .append(percentEncodedFileName)
                .append(";")
                .append("filename*=")
                .append("utf-8''")
                .append(percentEncodedFileName);

        response.setHeader("Content-disposition", contentDispositionValue.toString());
        response.setHeader("download-filename", percentEncodedFileName);
    }

    /**
     * Percent encoding utility method
     *
     * @param s String to be percent encoded
     * @return Percent encoded string
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException
    {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }
}
