package com.stylefeng.guns.rest.config;

import com.stylefeng.guns.core.config.DefaultFastjsonConfig;
import com.stylefeng.guns.rest.config.properties.RestProperties;
import com.stylefeng.guns.rest.modular.auth.converter.WithSignMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 签名校验messageConverter
 *
 * @author fengshuonan
 * @date 2017-08-25 16:04
 */
@Configuration
public class MessageConverConfig {

    @Bean
    // 根据配置文件中的属性来判断是否注入该 bean
    // prefix 属性的前缀
    // name 属性的名称
    // havingValue 比较的值
    // matchIfMissing 属性不存在时是否注入
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "sign-open", havingValue = "true", matchIfMissing = true)
    public WithSignMessageConverter withSignMessageConverter() {
        WithSignMessageConverter withSignMessageConverter = new WithSignMessageConverter();
        DefaultFastjsonConfig defaultFastjsonConfig = new DefaultFastjsonConfig();
        withSignMessageConverter.setFastJsonConfig(defaultFastjsonConfig.fastjsonConfig());
        withSignMessageConverter.setSupportedMediaTypes(defaultFastjsonConfig.getSupportedMediaType());
        return withSignMessageConverter;
    }
}
