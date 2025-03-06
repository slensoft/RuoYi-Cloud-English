package com.ruoyi.common.sensitive.utils;

import com.ruoyi.common.core.utils.StringUtils;

/**
 * Desensitization utility class
 *
 * @author ruoyi
 */
public class DesensitizedUtil
{
    /**
     * All characters of the password are replaced with *, e.g., ******
     *
     * @param password Password
     * @return Desensitized password
     */
    public static String password(String password)
    {
        if (StringUtils.isBlank(password))
        {
            return StringUtils.EMPTY;
        }
        return StringUtils.repeat('*', password.length());
    }

    /**
     * Replace the middle of the license plate with *, if it is an incorrect license plate, do not process
     *
     * @param carLicense Complete license plate number
     * @return Desensitized license plate
     */
    public static String carLicense(String carLicense)
    {
        if (StringUtils.isBlank(carLicense))
        {
            return StringUtils.EMPTY;
        }
        // Ordinary license plate
        if (carLicense.length() == 7)
        {
            carLicense = StringUtils.hide(carLicense, 3, 6);
        }
        else if (carLicense.length() == 8)
        {
            // New energy license plate
            carLicense = StringUtils.hide(carLicense, 3, 7);
        }
        return carLicense;
    }
}
