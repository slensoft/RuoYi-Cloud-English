package com.ruoyi.common.sensitive.enums;

import java.util.function.Function;
import com.ruoyi.common.sensitive.utils.DesensitizedUtil;

/**
 * Desensitization type
 *
 * @author ruoyi
 */
public enum DesensitizedType
{
    /**
     * Name, replace the second character with an asterisk
     */
    USERNAME(s -> s.replaceAll("(\\S)\\S(\\S*)", "$1*$2")),

    /**
     * Password, all characters are replaced with *
     */
    PASSWORD(DesensitizedUtil::password),

    /**
     * ID card, replace the middle 10 digits with asterisks
     */
    ID_CARD(s -> s.replaceAll("(\\d{4})\\d{10}(\\d{3}[Xx]|\\d{4})", "$1** **** ****$2")),

    /**
     * Phone number, replace the middle 4 digits with asterisks
     */
    PHONE(s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")),

    /**
     * Email, only the first letter and the address after @ are displayed, others are replaced with asterisks
     */
    EMAIL(s -> s.replaceAll("(^.)[^@]*(@.*$)", "$1****$2")),

    /**
     * Bank card number, keep the last 4 digits, others are replaced with asterisks
     */
    BANK_CARD(s -> s.replaceAll("\\d{15}(\\d{3})", "**** **** **** **** $1")),

    /**
     * License plate number, including ordinary vehicles and new energy vehicles
     */
    CAR_LICENSE(DesensitizedUtil::carLicense);

    private final Function<String, String> desensitizer;

    DesensitizedType(Function<String, String> desensitizer)
    {
        this.desensitizer = desensitizer;
    }

    public Function<String, String> desensitizer()
    {
        return desensitizer;
    }
}
