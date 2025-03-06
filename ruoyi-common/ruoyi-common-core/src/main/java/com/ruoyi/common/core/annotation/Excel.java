package com.ruoyi.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import com.ruoyi.common.core.utils.poi.ExcelHandlerAdapter;

/**
 * Custom export Excel data annotation
 *
 * @author ruoyi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel
{
    /**
     * Sort in excel when exporting
     */
    public int sort() default Integer.MAX_VALUE;

    /**
     * Name exported to Excel.
     */
    public String name() default "";

    /**
     * Date format, such as: yyyy-MM-dd
     */
    public String dateFormat() default "";

    /**
     * Read content conversion expression (e.g.: 0=male,1=female,2=unknown)
     */
    public String readConverterExp() default "";

    /**
     * Separator, read string group content
     */
    public String separator() default ",";

    /**
     * BigDecimal precision default: -1 (BigDecimal formatting is not enabled by default)
     */
    public int scale() default -1;

    /**
     * BigDecimal rounding rule default: BigDecimal.ROUND_HALF_EVEN
     */
    public int roundingMode() default BigDecimal.ROUND_HALF_EVEN;

    /**
     * Export in excel when exporting the height of each column
     */
    public double height() default 14;

    /**
     * Export in excel when exporting the width of each column
     */
    public double width() default 16;

    /**
     * Text suffix, such as% 90 becomes 90%
     */
    public String suffix() default "";

    /**
     * Default value of the field when the value is empty
     */
    public String defaultValue() default "";

    /**
     * Prompt information
     */
    public String prompt() default "";

    /**
     * Whether to allow content to wrap
     */
    public boolean wrapText() default false;

    /**
     * Set the content that can only be selected but not input.
     */
    public String[] combo() default {};

    /**
     * Whether to merge cells vertically, for needs: containing list cell unit)
     */
    public boolean needMerge() default false;

    /**
     * Whether to export data, for needs: sometimes we need to export a template, this title is needed but the content needs to be filled in by the user manually.
     */
    public boolean isExport() default true;

    /**
     * Property name in another class, support multi-level acquisition, separated by dots
     */
    public String targetAttr() default "";

    /**
     * Whether to automatically count data, add a row of statistical data total at the end
     */
    public boolean isStatistics() default false;

    /**
     * Export type (0 number 1 string)
     */
    public ColumnType cellType() default ColumnType.STRING;

    /**
     * Export column header background color
     */
    public IndexedColors headerBackgroundColor() default IndexedColors.GREY_50_PERCENT;

    /**
     * Export column header font color
     */
    public IndexedColors headerColor() default IndexedColors.WHITE;

    /**
     * Export cell background color
     */
    public IndexedColors backgroundColor() default IndexedColors.WHITE;

    /**
     * Export cell font color
     */
    public IndexedColors color() default IndexedColors.BLACK;

    /**
     * Export field alignment
     */
    public HorizontalAlignment align() default HorizontalAlignment.CENTER;

    /**
     * Custom data processor
     */
    public Class<?> handler() default ExcelHandlerAdapter.class;

    /**
     * Custom data processor parameter
     */
    public String[] args() default {};

    /**
     * Field type (0: export and import; 1: export only; 2: import only)
     */
    Type type() default Type.ALL;

    public enum Type
    {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    public enum ColumnType
    {
        NUMERIC(0), STRING(1), IMAGE(2), TEXT(3);
        private final int value;

        ColumnType(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }
}
