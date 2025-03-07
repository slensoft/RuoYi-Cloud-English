package com.ruoyi.common.core.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.util.AntPathMatcher;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.text.StrFormatter;

/**
 * String Utility Class
 *
 * @author ruoyi
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils
{
    /** Empty string */
    private static final String NULLSTR = "";

    /** Underscore */
    private static final char SEPARATOR = '_';

    /** Asterisk */
    private static final char ASTERISK = '*';

    /**
     * Get non-null parameter value
     *
     * @param value defaultValue Value to check
     * @return value Return value
     */
    public static <T> T nvl(T value, T defaultValue)
    {
        return value != null ? value : defaultValue;
    }

    /**
     * Check if a Collection is empty, including List, Set, Queue
     *
     * @param coll Collection to check
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * Check if a Collection is not empty, including List, Set, Queue
     *
     * @param coll Collection to check
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * Check if an object array is empty
     *
     * @param objects Object array to check
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * Check if an object array is not empty
     *
     * @param objects Object array to check
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * Check if a Map is empty
     *
     * @param map Map to check
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * Check if a Map is not empty
     *
     * @param map Map to check
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * Check if a string is empty
     *
     * @param str String
     * @return true: empty false: not empty
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * Check if a string is not empty
     *
     * @param str String
     * @return true: not empty false: empty
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * Check if an object is null
     *
     * @param object Object
     * @return true: null false: not null
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * Check if an object is not null
     *
     * @param object Object
     * @return true: not null false: null
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * Check if an object is an array type (Java primitive type array)
     *
     * @param object Object
     * @return true: is array false: not array
     */
    public static boolean isArray(Object object)
    {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * Trim whitespace
     */
    public static String trim(String str)
    {
        return (str == null ? "" : str.trim());
    }

    /**
     * Replace characters with "*" in specified range of a string
     *
     * @param str String
     * @param startInclude Start position (inclusive)
     * @param endExclude End position (exclusive)
     * @return Modified string
     */
    public static String hide(CharSequence str, int startInclude, int endExclude)
    {
        if (isEmpty(str))
        {
            return NULLSTR;
        }
        final int strLength = str.length();
        if (startInclude > strLength)
        {
            return NULLSTR;
        }
        if (endExclude > strLength)
        {
            endExclude = strLength;
        }
        if (startInclude > endExclude)
        {
            // If start position is greater than end position, no replacement
            return NULLSTR;
        }
        final char[] chars = new char[strLength];
        for (int i = 0; i < strLength; i++)
        {
            if (i >= startInclude && i < endExclude)
            {
                chars[i] = ASTERISK;
            }
            else
            {
                chars[i] = str.charAt(i);
            }
        }
        return new String(chars);
    }

    /**
     * Substring
     *
     * @param str String
     * @param start Start
     * @return Result
     */
    public static String substring(final String str, int start)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = str.length() + start;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (start > str.length())
        {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * Substring
     *
     * @param str String
     * @param start Start
     * @param end End
     * @return Result
     */
    public static String substring(final String str, int start, int end)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (end < 0)
        {
            end = str.length() + end;
        }
        if (start < 0)
        {
            start = str.length() + start;
        }

        if (end > str.length())
        {
            end = str.length();
        }

        if (start > end)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 在字符串中查找第一个出现的 `open` 和最后一个出现的 `close` 之间的子字符串
     *
     * @param str 要截取的字符串
     * @param open 起始字符串
     * @param close 结束字符串
     * @return 截取结果
     */
    public static String substringBetweenLast(final String str, final String open, final String close)
    {
        if (isEmpty(str) || isEmpty(open) || isEmpty(close))
        {
            return NULLSTR;
        }
        final int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND)
        {
            final int end = str.lastIndexOf(close);
            if (end != INDEX_NOT_FOUND)
            {
                return str.substring(start + open.length(), end);
            }
        }
        return NULLSTR;
    }

    /**
     * Check if string is not empty and not whitespace
     *
     * @param str Value to check
     * @return Result
     */
    public static boolean hasText(String str)
    {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str)
    {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Format text, {} represents placeholder<br>
     * This method simply replaces {} placeholders with parameters in order<br>
     * To output {}, use \\ to escape {, to output \ before {}, use double escape \\\\ <br>
     * Example:<br>
     * Normal usage: format("this is {} for {}", "a", "b") -> this is a for b<br>
     * Escape {}: format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * Escape \: format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template Text template, parts to be replaced marked with {}
     * @param params Parameter values
     * @return Formatted text
     */
    public static String format(String template, Object... params)
    {
        if (isEmpty(params) || isEmpty(template))
        {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * Check if starts with http(s)://
     *
     * @param link Link
     * @return Result
     */
    public static boolean ishttp(String link)
    {
        return StringUtils.startsWithAny(link, Constants.HTTP, Constants.HTTPS);
    }

    /**
     * Check if given collection contains any element from array
     *
     * @param collection Given collection
     * @param array Given array
     * @return boolean Result
     */
    public static boolean containsAny(Collection<String> collection, String... array)
    {
        if (isEmpty(collection) || isEmpty(array))
        {
            return false;
        }
        else
        {
            for (String str : array)
            {
                if (collection.contains(str))
                {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Convert camelCase to under_score case
     */
    public static String toUnderScoreCase(String str)
    {
        if (str == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // Previous character is uppercase
        boolean preCharIsUpperCase = true;
        // Current character is uppercase
        boolean curreCharIsUpperCase = true;
        // Next character is uppercase
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (i > 0)
            {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            }
            else
            {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1))
            {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase)
            {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * Check if string is in array
     *
     * @param str String to verify
     * @param strs String array
     * @return Returns true if contained
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s : strs)
            {
                if (str.equalsIgnoreCase(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Convert underscore uppercase string to camelCase. If the input string is empty, returns empty string.
     * Example: HELLO_WORLD -> HelloWorld
     *
     * @param name Input string in underscore uppercase format
     * @return Output string in camelCase format
     */
    public static String convertToCamelCase(String name)
    {
        StringBuilder result = new StringBuilder();
        // Quick check
        if (name == null || name.isEmpty())
        {
            // No need to convert
            return "";
        }
        else if (!name.contains("_"))
        {
            // No underscore, just capitalize first letter
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // Split original string by underscore
        String[] camels = name.split("_");
        for (String camel : camels)
        {
            // Skip leading, trailing, or double underscores
            if (camel.isEmpty())
            {
                continue;
            }
            // Capitalize first letter
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * CamelCase naming convention
     * Example: user_name -> userName
     */
    public static String toCamelCase(String s)
    {
        if (s == null)
        {
            return null;
        }
        if (s.indexOf(SEPARATOR) == -1)
        {
            return s;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (c == SEPARATOR)
            {
                upperCase = true;
            }
            else if (upperCase)
            {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            }
            else
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Check if specified string matches any string in the list
     *
     * @param str Specified string
     * @param strs String array to check against
     * @return Whether matches
     */
    public static boolean matches(String str, List<String> strs)
    {
        if (isEmpty(str) || isEmpty(strs))
        {
            return false;
        }
        for (String pattern : strs)
        {
            if (isMatch(pattern, str))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if URL matches pattern:
     * ? represents single character
     * * represents any string within one path level, cannot cross levels
     * ** represents any string across path levels
     *
     * @param pattern Matching pattern
     * @param url URL to match
     * @return
     */
    public static boolean isMatch(String pattern, String url)
    {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj)
    {
        return (T) obj;
    }

    /**
     * Pad number with zeros on the left to reach specified length.
     * Note: if number converted to string is longer than size, only keep last size characters.
     *
     * @param num Number object
     * @param size Specified string length
     * @return String format of number with specified length
     */
    public static final String padl(final Number num, final int size)
    {
        return padl(num.toString(), size, '0');
    }

    /**
     * Pad string on the left. If original string s is longer than size, only keep last size characters.
     *
     * @param s Original string
     * @param size Specified string length
     * @param c Character for padding
     * @return String with specified length, padded or truncated from original string
     */
    public static final String padl(final String s, final int size, final char c)
    {
        final StringBuilder sb = new StringBuilder(size);
        if (s != null)
        {
            final int len = s.length();
            if (s.length() <= size)
            {
                for (int i = size - len; i > 0; i--)
                {
                    sb.append(c);
                }
                sb.append(s);
            }
            else
            {
                return s.substring(len - size, len);
            }
        }
        else
        {
            for (int i = size; i > 0; i--)
            {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
