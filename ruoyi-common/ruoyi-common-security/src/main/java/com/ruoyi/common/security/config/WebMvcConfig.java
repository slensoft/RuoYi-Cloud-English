package com.ruoyi.common.security.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.ruoyi.common.security.interceptor.HeaderInterceptor;

/**
 * Interceptor configuration
 *
 * @author ruoyi
 */
public class WebMvcConfig implements WebMvcConfigurer
{
    /** Addresses that do not need to be intercepted */
    public static final String[] excludeUrls = { "/login", "/logout", "/refresh" };

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(getHeaderInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrls)
                .order(-10);
    }

    /**
     * Custom request header interceptor
     */
    public HeaderInterceptor getHeaderInterceptor()
    {
        return new HeaderInterceptor();
    }
}
