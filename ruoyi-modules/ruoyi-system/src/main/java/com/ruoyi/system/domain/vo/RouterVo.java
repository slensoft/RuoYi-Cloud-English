package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * Router configuration information
 * 
 * @author ruoyi
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo
{
    /**
     * Router name
     */
    private String name;

    /**
     * Router path
     */
    private String path;

    /**
     * Whether to hide the router, when set to true, this router will not appear in the sidebar
     */
    private boolean hidden;

    /**
     * Redirect address, when set to noRedirect, this router cannot be clicked in breadcrumb navigation
     */
    private String redirect;

    /**
     * Component path
     */
    private String component;

    /**
     * Router parameters: e.g. {"id": 1, "name": "ry"}
     */
    private String query;

    /**
     * When you have more than 1 route declared in children under a route, it will automatically become nested mode--like component pages
     */
    private Boolean alwaysShow;

    /**
     * Other meta elements
     */
    private MetaVo meta;

    /**
     * Child routes
     */
    private List<RouterVo> children;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public boolean getHidden()
    {
        return hidden;
    }

    public void setHidden(boolean hidden)
    {
        this.hidden = hidden;
    }

    public String getRedirect()
    {
        return redirect;
    }

    public void setRedirect(String redirect)
    {
        this.redirect = redirect;
    }

    public String getComponent()
    {
        return component;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public Boolean getAlwaysShow()
    {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow)
    {
        this.alwaysShow = alwaysShow;
    }

    public MetaVo getMeta()
    {
        return meta;
    }

    public void setMeta(MetaVo meta)
    {
        this.meta = meta;
    }

    public List<RouterVo> getChildren()
    {
        return children;
    }

    public void setChildren(List<RouterVo> children)
    {
        this.children = children;
    }
}
