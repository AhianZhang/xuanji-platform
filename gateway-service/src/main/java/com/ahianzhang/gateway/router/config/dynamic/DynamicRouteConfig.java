package com.ahianzhang.gateway.router.config.dynamic;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.ahianzhang.gateway.router.config.dynamic.nacos.NacosRouteDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ahianzhang
 * @version 1.0
 * @date 2020/8/11 5:50 PM
 **/
@Configuration
@ConditionalOnProperty(prefix = "xuanji.gateway.dynamic-route",name = "enable",havingValue = "true")
public class DynamicRouteConfig {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Configuration
    @ConditionalOnProperty(prefix = "xuanji.gateway.dynamic-route", name = "dataSource", havingValue = "nacos", matchIfMissing = true)
    public class NaocsDataSource{
        @Autowired
        private NacosConfigProperties nacosConfigProperties;

        @Bean
        public NacosRouteDefinitionRepository nacosRouteDefinitionRepository(){
            return new NacosRouteDefinitionRepository(publisher,nacosConfigProperties);
        }
    }
}
