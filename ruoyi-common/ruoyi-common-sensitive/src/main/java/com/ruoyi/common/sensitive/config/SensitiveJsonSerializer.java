package com.ruoyi.common.sensitive.config;

import java.io.IOException;
import java.util.Objects;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.common.sensitive.annotation.Sensitive;
import com.ruoyi.common.sensitive.enums.DesensitizedType;
import com.ruoyi.system.api.model.LoginUser;

/**
 * Data desensitization serialization filter
 *
 * @author ruoyi
 */
public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer
{
    private DesensitizedType desensitizedType;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException
    {
        if (desensitization())
        {
            gen.writeString(desensitizedType.desensitizer().apply(value));
        }
        else
        {
            gen.writeString(value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException
    {
        Sensitive annotation = property.getAnnotation(Sensitive.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass()))
        {
            this.desensitizedType = annotation.desensitizedType();
            return this;
        }
        return prov.findValueSerializer(property.getType(), property);
    }

    /**
     * Whether desensitization is needed
     */
    private boolean desensitization()
    {
        try
        {
            LoginUser securityUser = SecurityUtils.getLoginUser();
            // Administrators are not desensitized
            return !securityUser.getSysUser().isAdmin();
        }
        catch (Exception e)
        {
            return true;
        }
    }
}
