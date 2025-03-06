package com.ruoyi.common.swagger.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

/**
 * Swagger configuration properties
 *
 * @author ruoyi
 */
@ConfigurationProperties(prefix = "springdoc")
public class SpringDocProperties
{
    /**
     * Gateway
     */
    private String gatewayUrl;

    /**
     * Basic information of the document
     */
    @NestedConfigurationProperty
    private InfoProperties info = new InfoProperties();

    /**
     * <p>
     * Basic attribute information of the document
     * </p>
     * @see io.swagger.v3.oas.models.info.Info
     *
     * To automatically generate configuration prompt information for Spring Boot, a class is copied here
     */
    public static class InfoProperties
    {
        /**
         * Title
         */
        private String title = null;

        /**
         * Description
         */
        private String description = null;

        /**
         * Contact information
         */
        @NestedConfigurationProperty
        private Contact contact = null;

        /**
         * License
         */
        @NestedConfigurationProperty
        private License license = null;

        /**
         * Version
         */
        private String version = null;

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public Contact getContact()
        {
            return contact;
        }

        public void setContact(Contact contact)
        {
            this.contact = contact;
        }

        public License getLicense()
        {
            return license;
        }

        public void setLicense(License license)
        {
            this.license = license;
        }

        public String getVersion()
        {
            return version;
        }

        public void setVersion(String version)
        {
            this.version = version;
        }
    }

    public String getGatewayUrl()
    {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl)
    {
        this.gatewayUrl = gatewayUrl;
    }

    public InfoProperties getInfo()
    {
        return info;
    }

    public void setInfo(InfoProperties info)
    {
        this.info = info;
    }
}
