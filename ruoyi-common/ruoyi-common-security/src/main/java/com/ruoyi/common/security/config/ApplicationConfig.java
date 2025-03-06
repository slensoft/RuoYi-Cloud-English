package com.ruoyi.common.security.config;

import java.util.TimeZone;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * System configuration
 *
 * @author ruoyi
 */
public class ApplicationConfig
{
    /**
     * Time zone configuration
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
    {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
