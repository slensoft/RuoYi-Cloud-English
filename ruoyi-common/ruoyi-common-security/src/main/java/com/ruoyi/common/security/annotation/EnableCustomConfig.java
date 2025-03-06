package com.ruoyi.common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import com.ruoyi.common.security.config.ApplicationConfig;
import com.ruoyi.common.security.feign.FeignAutoConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// Expose the proxy object through the AOP framework, allowing AopContext to access
@EnableAspectJAutoProxy(exposeProxy = true)
// Specify the path of the Mapper class package to scan
@MapperScan("com.ruoyi.**.mapper")
// Enable asynchronous thread execution
@EnableAsync
// Auto-load classes
@Import({ ApplicationConfig.class, FeignAutoConfiguration.class })
public @interface EnableCustomConfig
{

}
