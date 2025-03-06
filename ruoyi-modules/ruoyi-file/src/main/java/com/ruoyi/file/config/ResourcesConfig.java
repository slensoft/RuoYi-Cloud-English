package com.ruoyi.file.config;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * General mapping configuration
 * 
 * @author ruoyi
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    /**
     * Root path for storing uploaded files locally
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * Resource mapping path prefix
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** Local file upload path */
        registry.addResourceHandler(localFilePrefix + "/**")
                .addResourceLocations("file:" + localFilePath + File.separator);
    }
    
    /**
     * Enable cross-origin
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Set allowed cross-origin routes
        registry.addMapping(localFilePrefix  + "/**")
                // Set allowed cross-origin request domains
                .allowedOrigins("*")
                // Set allowed methods
                .allowedMethods("GET");
    }
}
