package com.ruoyi.gateway.config.properties;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Whitelist configuration
 * 
 * @author ruoyi
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreWhiteProperties
{
    /**
     * Whitelist configuration, the gateway does not verify the whitelist here
     */
    private List<String> whites = new ArrayList<>();

    public List<String> getWhites()
    {
        return whites;
    }

    public void setWhites(List<String> whites)
    {
        this.whites = whites;
    }
}
