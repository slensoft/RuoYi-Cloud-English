package com.ruoyi.system.api.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * File information
 * 
 * @author ruoyi
 */
public class SysFile
{
    /**
     * File name
     */
    private String name;

    /**
     * File address
     */
    private String url;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("name", getName())
            .append("url", getUrl())
            .toString();
    }
}
