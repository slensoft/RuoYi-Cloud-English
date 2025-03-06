package com.ruoyi.gateway.config;

import java.util.Set;
import java.util.stream.Collectors;
import org.springdoc.core.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import com.alibaba.nacos.client.naming.event.InstancesChangeEvent;
import com.alibaba.nacos.common.notify.Event;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.Subscriber;
import com.ruoyi.common.core.utils.StringUtils;

/**
 * SpringDoc configuration class
 *
 * @author ruoyi
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", matchIfMissing = true)
public class SpringDocConfig implements InitializingBean
{
    @Autowired
    private SwaggerUiConfigProperties swaggerUiConfigProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * Method called after initialization
     */
    @Override
    public void afterPropertiesSet()
    {
        NotifyCenter.registerSubscriber(new SwaggerDocRegister(swaggerUiConfigProperties, discoveryClient));
    }
}

/**
 * Swagger document registration
 */
class SwaggerDocRegister extends Subscriber<InstancesChangeEvent>
{
    @Autowired
    private SwaggerUiConfigProperties swaggerUiConfigProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    private final static String[] EXCLUDE_ROUTES = new String[] { "ruoyi-gateway", "ruoyi-auth", "ruoyi-file", "ruoyi-monitor" };

    public SwaggerDocRegister(SwaggerUiConfigProperties swaggerUiConfigProperties, DiscoveryClient discoveryClient)
    {
        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
        this.discoveryClient = discoveryClient;
    }

    /**
     * Event callback method, handle InstancesChangeEvent event
     * @param event Event object
     */
    @Override
    public void onEvent(InstancesChangeEvent event)
    {
        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrlSet = discoveryClient.getServices()
            .stream()
            .flatMap(serviceId -> discoveryClient.getInstances(serviceId).stream())
            .filter(instance -> !StringUtils.equalsAnyIgnoreCase(instance.getServiceId(), EXCLUDE_ROUTES))
            .map(instance -> {
                AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
                swaggerUrl.setName(instance.getServiceId());
                swaggerUrl.setUrl(String.format("/%s/v3/api-docs", instance.getServiceId()));
                return swaggerUrl;
            })
            .collect(Collectors.toSet());

        swaggerUiConfigProperties.setUrls(swaggerUrlSet);
    }

    /**
     * Subscription type method, return the subscribed event type
     * @return Subscribed event type
     */
    @Override
    public Class<? extends Event> subscribeType()
    {
        return InstancesChangeEvent.class;
    }
}
