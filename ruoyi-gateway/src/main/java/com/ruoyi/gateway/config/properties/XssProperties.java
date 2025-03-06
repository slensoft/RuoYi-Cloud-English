package com.ruoyi.gateway.config.properties;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * XSS cross-site scripting configuration
 * 
 * @author ruoyi
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.xss")
public class XssProperties
{
    /**
     * Xss switch
     */
    private Boolean enabled;

    /**
     * Exclude paths
     */
    private List<String> excludeUrls = new ArrayList<>();

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public List<String> getExcludeUrls()
    {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls)
    {
        this.excludeUrls = excludeUrls;
    }
}
