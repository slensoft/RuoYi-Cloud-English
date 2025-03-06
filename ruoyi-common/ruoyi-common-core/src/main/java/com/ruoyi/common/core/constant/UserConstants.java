package com.ruoyi.common.core.constant;

/**
 * User constant information
 *
 * @author ruoyi
 */
public class UserConstants
{
    /**
     * Unique identifier for system users within the platform
     */
    public static final String SYS_USER = "SYS_USER";

    /** Normal status */
    public static final String NORMAL = "0";

    /** Exception status */
    public static final String EXCEPTION = "1";

    /** User ban status */
    public static final String USER_DISABLE = "1";

    /** Role normal status */
    public static final String ROLE_NORMAL = "0";

    /** Role ban status */
    public static final String ROLE_DISABLE = "1";

    /** Department normal status */
    public static final String DEPT_NORMAL = "0";

    /** Department disable status */
    public static final String DEPT_DISABLE = "1";

    /** Dictionary normal status */
    public static final String DICT_NORMAL = "0";

    /** Is it a system default (yes) */
    public static final String YES = "Y";

    /** Is it an external link (yes) */
    public static final String YES_FRAME = "0";

    /** Is it an external link (no) */
    public static final String NO_FRAME = "1";

    /** Menu type (directory) */
    public static final String TYPE_DIR = "M";

    /** Menu type (menu) */
    public static final String TYPE_MENU = "C";

    /** Menu type (button) */
    public static final String TYPE_BUTTON = "F";

    /** Layout component identifier */
    public final static String LAYOUT = "Layout";

    /** ParentView component identifier */
    public final static String PARENT_VIEW = "ParentView";

    /** InnerLink component identifier */
    public final static String INNER_LINK = "InnerLink";

    /** Check if unique return identifier */
    public final static boolean UNIQUE = true;
    public final static boolean NOT_UNIQUE = false;

    /**
     * Username length limit
     */
    public static final int USERNAME_MIN_LENGTH = 2;

    public static final int USERNAME_MAX_LENGTH = 20;

    /**
     * Password length limit
     */
    public static final int PASSWORD_MIN_LENGTH = 5;

    public static final int PASSWORD_MAX_LENGTH = 20;

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
