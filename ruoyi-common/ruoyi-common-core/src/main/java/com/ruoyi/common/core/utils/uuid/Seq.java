package com.ruoyi.common.core.utils.uuid;

import java.util.concurrent.atomic.AtomicInteger;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;

/**
 * @author ruoyi Sequence Generator Class
 */
public class Seq
{
    // Common sequence type
    public static final String commSeqType = "COMMON";

    // Upload sequence type
    public static final String uploadSeqType = "UPLOAD";

    // Common interface sequence counter
    private static AtomicInteger commSeq = new AtomicInteger(1);

    // Upload interface sequence counter
    private static AtomicInteger uploadSeq = new AtomicInteger(1);

    // Machine identifier
    private static final String machineCode = "A";

    /**
     * Get common sequence number
     * 
     * @return Sequence value
     */
    public static String getId()
    {
        return getId(commSeqType);
    }
    
    /**
     * Default 16-digit sequence number: yyMMddHHmmss + one machine identifier + 3-digit incrementing string
     * 
     * @return Sequence value
     */
    public static String getId(String type)
    {
        AtomicInteger atomicInt = commSeq;
        if (uploadSeqType.equals(type))
        {
            atomicInt = uploadSeq;
        }
        return getId(atomicInt, 3);
    }

    /**
     * Common interface sequence number: yyMMddHHmmss + one machine identifier + incrementing string of specified length
     * 
     * @param atomicInt Sequence counter
     * @param length Value length
     * @return Sequence value
     */
    public static String getId(AtomicInteger atomicInt, int length)
    {
        String result = DateUtils.dateTimeNow();
        result += machineCode;
        result += getSeq(atomicInt, length);
        return result;
    }

    /**
     * Incrementing sequence string [1, 10^length), left-padded with zeros to length digits
     * 
     * @return Sequence value
     */
    private synchronized static String getSeq(AtomicInteger atomicInt, int length)
    {
        // Get value then increment
        int value = atomicInt.getAndIncrement();

        // Reset to 1 if updated value >= 10^length
        int maxSeq = (int) Math.pow(10, length);
        if (atomicInt.get() >= maxSeq)
        {
            atomicInt.set(1);
        }
        // Convert to string, left-pad with zeros
        return StringUtils.padl(value, length);
    }
}
