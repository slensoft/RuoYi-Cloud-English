package com.ruoyi.gen.util;

import java.util.Properties;
import org.apache.velocity.app.Velocity;
import com.ruoyi.common.core.constant.Constants;

/**
 * VelocityEngine factory
 * 
 * @author ruoyi
 */
public class VelocityInitializer
{
    /**
     * Initialize vm method
     */
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            // Load vm files in the classpath directory
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // Define character set
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // Initialize Velocity engine, specify configuration Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
